package com.matejdev.demo.base.rx.usecase

import com.matejdev.demo.base.rx.RxSchedulersFactory
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseRxUseCase
constructor(
    private val rxSchedulersFactory: RxSchedulersFactory
) {

    private val disposables = CompositeDisposable()

    fun dispose() {
        if (!disposables.isDisposed) disposables.dispose()
    }

    protected fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }

    protected open fun backgroundThread(): Scheduler = rxSchedulersFactory.ioScheduler()

    protected open fun foregroundThread(): Scheduler = rxSchedulersFactory.uiSheduler()
}
