package com.matejdev.demo.base.rx.observer

import io.reactivex.observers.DisposableObserver

/**
 * Handy empty implementation of [DisposableObserver]
 */
class EmptyObserver<T> : DisposableObserver<T>() {

    override fun onNext(result: T) {}

    override fun onError(e: Throwable) {}

    override fun onComplete() {}
}
