package com.matejdev.demo.base.rx.observer

import io.reactivex.observers.DisposableCompletableObserver

/**
 * Handy empty implementation of [DisposableCompletableObserver]
 */
open class EmptyCompletableObserver : DisposableCompletableObserver() {

    override fun onComplete() {
    }

    override fun onError(e: Throwable) {
    }
}
