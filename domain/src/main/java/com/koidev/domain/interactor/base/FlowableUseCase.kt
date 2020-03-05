package com.koidev.domain.interactor.base

import com.koidev.domain.common.disposedBy
import com.koidev.domain.executor.PostExecutionThread
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.DisposableSubscriber

abstract class FlowableUseCase<T, in Params> constructor(
    private val postExecutionThread: PostExecutionThread
) {

    private val disposables = CompositeDisposable()

    abstract fun buildUseCaseObservable(params: Params? = null): Flowable<T>

    open fun execute(observer: DisposableSubscriber<T>, params: Params? = null) {
        val flowable = this.buildUseCaseObservable(params)
            .subscribeOn(Schedulers.io())
            .observeOn(postExecutionThread.scheduler)

        flowable.subscribeWith(observer).disposedBy(disposables)
    }

    fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }

    open fun dispose() {
        if (!disposables.isDisposed) disposables.dispose()
    }

    fun clear() {
        disposables.clear()
    }

}