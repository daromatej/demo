package com.matejdev.demo.base.rx.usecase

import com.matejdev.demo.base.rx.RxSchedulersFactory
import com.matejdev.demo.base.rx.observer.EmptySingleObserver
import io.reactivex.Single
import io.reactivex.observers.DisposableSingleObserver

abstract class SingleUseCase<In, Out>
constructor(
    rxSchedulersFactory: RxSchedulersFactory
) : BaseRxUseCase(rxSchedulersFactory) {

    abstract fun useCase(params: In?): Single<Out>

    fun execute(
        observer: DisposableSingleObserver<Out> = EmptySingleObserver(),
        params: In? = null
    ) {
        useCase(params)
            .subscribeOn(backgroundThread())
            .observeOn(foregroundThread())
            .subscribeWith(observer)
            .let { addDisposable(it) }
    }
}
