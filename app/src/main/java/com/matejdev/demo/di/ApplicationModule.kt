package com.matejdev.demo.di

import android.app.Application
import android.content.Context.CONNECTIVITY_SERVICE
import android.content.res.Resources
import android.net.ConnectivityManager
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule {

    @Provides
    fun provideResources(application: Application): Resources = application.resources

    @Provides
    fun provideConnectivityManager(application: Application): ConnectivityManager = application.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
}

