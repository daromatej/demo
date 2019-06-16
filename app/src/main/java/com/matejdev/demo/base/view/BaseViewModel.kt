package com.matejdev.demo.base.view

import androidx.lifecycle.ViewModel

/**
 * Base interface for all ViewModels in the app
 */
abstract class BaseViewModel : ViewModel() {

    open fun init() {}
}
