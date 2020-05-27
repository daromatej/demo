package com.matejdev.demo.base.rx

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Factory of Rx schedulers to be used by use cases
 */
class RxSchedulersFactory
@Inject constructor() {

    fun ioScheduler(): Scheduler = Schedulers.io()

    fun computationScheduler(): Scheduler = Schedulers.computation()

    fun uiScheduler(): Scheduler = AndroidSchedulers.mainThread()
}
