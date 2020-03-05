package com.koidev.domain.interactor.base

import com.koidev.domain.common.disposedBy
import com.koidev.domain.executor.PostExecutionThread
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

abstract class SingleUseCase<T, in Params> constructor(
    private val postExecutionThread: PostExecutionThread
) {

    private val subscriptions = CompositeDisposable()

    abstract fun buildUseCaseObservable(params: Params? = null): Single<T>

    open fun execute(observer: DisposableSingleObserver<T>, params: Params? = null) {
        val observable = this.buildUseCaseObservable(params)
            .subscribeOn(Schedulers.io())
            .observeOn(postExecutionThread.scheduler)

        observable.subscribeWith(observer).disposedBy(subscriptions)
    }

    open fun dispose() {
        subscriptions.clear()
    }

}