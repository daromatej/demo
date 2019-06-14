package com.matejdev.demo.base.rx

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RxSchedulersFactory
@Inject constructor() {

    fun ioScheduler() = Schedulers.io()

    fun computationSheduler() = Schedulers.computation()

    fun uiSheduler() = AndroidSchedulers.mainThread()
}
