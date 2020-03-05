package com.koidev.domain.common

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

fun Disposable.disposedBy(subscriptions: CompositeDisposable) {
    subscriptions.add(this)
}