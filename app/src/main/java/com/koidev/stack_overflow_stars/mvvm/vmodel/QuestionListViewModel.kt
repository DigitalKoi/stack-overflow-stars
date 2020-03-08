package com.koidev.stack_overflow_stars.mvvm.vmodel

import androidx.lifecycle.ViewModel
import com.jakewharton.rxrelay2.BehaviorRelay
import com.koidev.domain.Question
import com.koidev.domain.common.disposedBy
import com.koidev.domain.interactor.GetQuestionsList
import com.koidev.stack_overflow_stars.navigation.Screens
import com.koidev.stack_overflow_stars.utils.Paginator
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import ru.terrakok.cicerone.Router
import timber.log.Timber

class QuestionListViewModel(
    private val router: Router,
    private val getQuestionsList: GetQuestionsList,
    private val paginator: Paginator.Store<Question>
) : ViewModel() {

    private val subscriptions by lazy { CompositeDisposable() }

    private val renderState = BehaviorRelay.create<Paginator.State>()

    init {
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

    private inner class ObserveGetQuestionsList(val page: Int) : DisposableObserver<List<Question>>() {

        override fun onComplete() {
            Timber.d("Observe questions stream completed")
        }

        override fun onNext(list: List<Question>) {
            paginator.proceed(Paginator.Action.NewPage(page, list))
        }

        override fun onError(e: Throwable) {
            paginator.proceed(Paginator.Action.PageError(e))
            Timber.d("Observing questions list error: ${e.localizedMessage}")
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

    private fun loadNewPage(page: Int) {
        getQuestionsList.execute(
            observer = ObserveGetQuestionsList(page),
            params = page
        )
    }

    private fun refreshProject() {
        paginator.proceed(Paginator.Action.Refresh)
    }
}