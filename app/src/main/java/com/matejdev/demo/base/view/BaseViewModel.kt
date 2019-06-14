package com.matejdev.demo.base.view

import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    open fun init() {}
}
