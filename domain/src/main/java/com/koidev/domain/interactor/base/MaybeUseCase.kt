package com.koidev.domain.interactor.base

import com.koidev.domain.common.disposedBy
import com.koidev.domain.executor.PostExecutionThread
import io.reactivex.Maybe
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableMaybeObserver
import io.reactivex.schedulers.Schedulers

abstract class MaybeUseCase<T, in Params> constructor(
        private val postExecutionThread: PostExecutionThread
) {

    private val subscriptions = CompositeDisposable()

    abstract fun buildUseCaseObservable(params: Params? = null): Maybe<T>

    open fun execute(observer: DisposableMaybeObserver<T>, params: Params? = null) {
        val maybe = this.buildUseCaseObservable(params)
                .subscribeOn(Schedulers.io())
                .observeOn(postExecutionThread.scheduler)

        maybe.subscribeWith(observer).disposedBy(subscriptions)
    }

    open fun dispose() {
        subscriptions.clear()
    }

}