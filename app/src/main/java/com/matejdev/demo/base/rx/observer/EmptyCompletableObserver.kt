package com.matejdev.demo.base.rx.observer

import io.reactivex.observers.DisposableCompletableObserver

open class EmptyCompletableObserver : DisposableCompletableObserver() {

    override fun onComplete() {
    }

    override fun onError(e: Throwable) {
    }
}
