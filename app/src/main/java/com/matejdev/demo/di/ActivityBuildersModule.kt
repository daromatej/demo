package com.matejdev.demo.di

import com.matejdev.demo.presentation.view.BrowserActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class ActivityBuildersModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun contributeBrowserActivity(): BrowserActivity
}
