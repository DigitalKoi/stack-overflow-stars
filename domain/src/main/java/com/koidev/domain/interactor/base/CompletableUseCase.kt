package com.koidev.domain.interactor.base

import com.koidev.domain.executor.PostExecutionThread
import io.reactivex.Completable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

abstract class CompletableUseCase<in Params> constructor(
    private val postExecutionThread: PostExecutionThread
) {

    private val subscriptions = CompositeDisposable()

    abstract fun buildUseCaseCompletable(params: Params? = null): Completable

    open fun execute(params: Params? = null) =
        this.buildUseCaseCompletable(params)
            .subscribeOn(Schedulers.io())
            .observeOn(postExecutionThread.scheduler)

    open fun dispose() {
        subscriptions.clear()
    }

    open fun subscriber(): CompositeDisposable = subscriptions
}