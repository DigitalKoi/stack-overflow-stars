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
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableSingleObserver
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

    fun onItemClick(item: Question) {

    }

    fun routeToQuestionsList() {
        router.navigateTo(Screens.QuestionsListScreen())
    }

    fun loadNextEventsPage() = paginator.proceed(Paginator.Action.LoadMore)

    fun refreshEvents() = paginator.proceed(Paginator.Action.Refresh)


    private inner class ObserveGetQuestionsList : DisposableCompletableObserver() {

        override fun onError(e: Throwable) {
            paginator.proceed(Paginator.Action.PageError(e))
            Timber.e("Loading questions list from network error: ${e.localizedMessage}")
        }

        override fun onComplete() {
            Timber.e("Loading questions list from network success")
            getQuestionsListFromDataBase()
        }
    }

    private inner class ObserveClearDataBase : DisposableCompletableObserver() {

        override fun onComplete() {
            Timber.d("Clearing database complete")
        }

        override fun onError(e: Throwable) {
            Timber.e("Clearing database error: ${e.localizedMessage}")
        }

    }

    private inner class ObserveGetQuestionsFromDB : DisposableSingleObserver<List<Question>>() {

        override fun onSuccess(t: List<Question>) {
            paginator.proceed(Paginator.Action.NewPage(page, t))
        }

        override fun onError(e: Throwable) {
            Timber.e("Loading questions list from database error: ${e.localizedMessage}")
        }


    }

    fun onBackPressed() {
        router.exit()
    }

    override fun onCleared() {
        super.onCleared()
        subscriptions.clear()
        getQuestionsList.dispose()
    }

    private fun getQuestionsListFromDataBase() {
        observeQuestionsListFromDataBase.execute(
            observer = ObserveGetQuestionsFromDB(),
            params = ""
        )
    }

    private fun loadNewPage(page: Int) {
        this.page = page
        getQuestionsList.execute(
            observer = ObserveGetQuestionsList(),
            params = page
        )
    }

    private fun clearDataBase() {
        clearDataBase.execute(
            observer = ObserveClearDataBase()
        )
    }

    private fun refreshProject() {
        paginator.proceed(Paginator.Action.Refresh)
    }
}