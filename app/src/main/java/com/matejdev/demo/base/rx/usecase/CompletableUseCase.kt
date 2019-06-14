package com.matejdev.demo.base.rx.usecase

import com.matejdev.demo.base.rx.RxSchedulersFactory
import com.matejdev.demo.base.rx.observer.EmptyCompletableObserver
import io.reactivex.Completable
import io.reactivex.observers.DisposableCompletableObserver

abstract class CompletableUseCase<In>
constructor(
    rxSchedulersFactory: RxSchedulersFactory
) : BaseRxUseCase(rxSchedulersFactory) {

    abstract fun useCase(params: In?): Completable

    fun execute(
        observer: DisposableCompletableObserver = EmptyCompletableObserver(),
        params: In? = null
    ) {
        useCase(params)
            .subscribeOn(backgroundThread())
            .observeOn(foregroundThread())
            .subscribeWith(observer)
            .let { addDisposable(it) }
    }
}
