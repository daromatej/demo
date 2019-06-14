package com.matejdev.demo.base.util

import com.matejdev.demo.BuildConfig
import timber.log.Timber

abstract class LogUtil {

    companion object {
        protected const val SUCCESS = "SUCCESS"
        protected const val FAILURE = "FAILURE"
        protected const val CLICKED = "CLICKED"

        fun init() {
            if (BuildConfig.DEBUG) {
                Timber.plant(Timber.DebugTree())
            }
        }

        inline fun logSuccess(value: Any? = null) {
            Timber.d("$SUCCESS %s", value?.toString())
        }

        inline fun logFailure(value: Any? = null) {
            Timber.d("$FAILURE %s", value?.toString())
        }

        inline fun logClick(value: Any? = null) {
            Timber.d("$CLICKED %s", value?.toString())
        }
    }
}
