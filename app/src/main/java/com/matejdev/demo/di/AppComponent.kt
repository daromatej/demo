package com.matejdev.demo.di

import android.app.Application
import com.matejdev.demo.DemoApplication
import com.matejdev.demo.presentation.view.BrowserActivity
import dagger.BindsInstance
import dagger.Component
import dagger.Provides
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    ActivityBuildersModule::class,
    ViewModelModule::class,
    RepositoryModule::class])
interface AppComponent : AndroidInjector<DemoApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    override fun inject(application: DemoApplication)
}
