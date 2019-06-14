package com.matejdev.demo.base.util

import android.net.ConnectivityManager
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ConnectionMonitor
@Inject constructor(
    private val connectivityManager: ConnectivityManager
) {

    fun isConnected() = connectivityManager.activeNetworkInfo.isConnected
}
