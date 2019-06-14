package com.matejdev.demo.di

import com.matejdev.demo.presentation.navigator.BrowserNavigator
import com.matejdev.demo.presentation.view.BrowserActivity
import dagger.Module
import dagger.Provides

@Module
class BrowserActivityModule {

    @Provides
    fun provideBrowserNavigator(activity: BrowserActivity) = BrowserNavigator(activity)
}
