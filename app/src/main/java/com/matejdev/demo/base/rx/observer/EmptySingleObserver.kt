package com.matejdev.demo.base.rx.observer

import io.reactivex.observers.DisposableSingleObserver

/**
 * Handy empty implementation of [DisposableSingleObserver]
 */
open class EmptySingleObserver<T> : DisposableSingleObserver<T>() {

    override fun onSuccess(result: T) {
    }

    override fun onError(e: Throwable) {
    }
}
