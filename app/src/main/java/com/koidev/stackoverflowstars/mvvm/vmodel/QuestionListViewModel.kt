package com.koidev.stackoverflowstars.mvvm.vmodel

import androidx.lifecycle.ViewModel
import com.jakewharton.rxrelay2.BehaviorRelay
import com.koidev.domain.Question
import com.koidev.domain.common.disposedBy
import com.koidev.domain.interactor.ClearDataBase
import com.koidev.domain.interactor.GetQuestionsList
import com.koidev.domain.interactor.ObserveQuestionsListFromDataBase
import com.koidev.stackoverflowstars.navigation.Screens
import com.koidev.stackoverflowstars.utils.Paginator
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import ru.terrakok.cicerone.Router
import timber.log.Timber

class QuestionListViewModel(
    private val router: Router,
    private val getQuestionsList: GetQuestionsList,
    private val observeQuestionsListFromDataBase: ObserveQuestionsListFromDataBase,
    private val clearDataBase: ClearDataBase,
    private val paginator: Paginator.Store<Question>
) : ViewModel() {

    private val subscriptions by lazy { CompositeDisposable() }

    private val renderState = BehaviorRelay.create<Paginator.State>()

    private val historyQuestionList = BehaviorRelay.create<List<Question>>()

    private var page = 1

    init {

        clearDataBase()

        paginator.render = { renderState.accept(it) }
        paginator.sideEffects.subscribe { effect ->
            when (effect) {
                is Paginator.SideEffect.LoadPage -> loadNewPage(effect.currentPage)
                is Paginator.SideEffect.ErrorEvent -> {
                    //TODO: add error handler
                }
            }
        }.disposedBy(subscriptions)

        refreshProject()
    }

    fun observeRenderState(): Observable<Paginator.State> = renderState

    fun observeHistoryList(): Observable<List<Question>> = historyQuestionList

    fun onItemClick(item: Question) {

    }

    fun routeToQuestionsList() {
        router.replaceScreen(Screens.QuestionsListScreen())
    }

    fun loadNextEventsPage() = paginator.proceed(Paginator.Action.LoadMore)

    fun refreshEvents() = paginator.proceed(Paginator.Action.Refresh)

    fun onBackPressed() {
        router.exit()
    }

    override fun onCleared() {
        super.onCleared()
        subscriptions.clear()
        getQuestionsList.dispose()
    }

    fun getQuestionsListFromDataBase(query: String = "") {
        observeQuestionsListFromDataBase.execute(query)
            .subscribeBy(
                {
                    Timber.e("Loading questions list from database error: ${it.localizedMessage}")
                },
                {
                    paginator.proceed(Paginator.Action.NewPage(page, it))
                    //TODO: move to other fragment
                    historyQuestionList.accept(it)
                    if (it.isEmpty() && query == "") refreshProject()
                    Timber.e("Loading questions list from database success with size list ${it.size}")
                }
            ).disposedBy(subscriptions)
    }

    private fun loadNewPage(page: Int) {
        getQuestionsList.execute(page)
            .subscribeBy(
                {
                    paginator.proceed(Paginator.Action.PageError(it))
                },
                {
                    Timber.e("Loading questions list from network success with page $page")
                    getQuestionsListFromDataBase()
                }
            )
            .disposedBy(subscriptions)
    }

    private fun clearDataBase() {
        clearDataBase.execute()
            .subscribeBy(
                {
                    Timber.e("Clearing database error: ${it.localizedMessage}")
                },
                {
                    Timber.d("Clearing database complete")
                }
            ).disposedBy(subscriptions)
    }

    private fun refreshProject() {
        paginator.proceed(Paginator.Action.Refresh)
    }
}