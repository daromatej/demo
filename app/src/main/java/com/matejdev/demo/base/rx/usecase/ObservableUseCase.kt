package com.matejdev.demo.base.rx.usecase

import com.matejdev.demo.base.rx.RxSchedulersFactory
import com.matejdev.demo.base.rx.observer.EmptyObserver
import io.reactivex.Observable
import io.reactivex.observers.DisposableObserver

abstract class ObservableUseCase<In, Out>
constructor(
    rxSchedulersFactory: RxSchedulersFactory
) : BaseRxUseCase(rxSchedulersFactory) {

    abstract fun useCase(params: In?): Observable<Out>

    fun execute(
        observer: DisposableObserver<Out> = EmptyObserver(),
        params: In? = null
    ) {
        useCase(params)
            .subscribeOn(backgroundThread())
            .observeOn(foregroundThread())
            .subscribeWith(observer)
            .let { addDisposable(it) }
    }
}
