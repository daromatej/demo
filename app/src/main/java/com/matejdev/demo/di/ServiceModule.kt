package com.matejdev.demo.di

import android.content.res.Resources
import com.matejdev.demo.R
import com.matejdev.demo.data.service.CommentService
import com.matejdev.demo.data.service.PostService
import com.matejdev.demo.data.service.UserService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [ApplicationModule::class])
class ServiceModule {

    @Singleton
    @Provides
    fun provideRetrofit(resources: Resources): Retrofit = Retrofit.Builder()
        .baseUrl(resources.getString(R.string.service_url))
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    fun providePostService(retrofit: Retrofit): PostService =
        retrofit.create(PostService::class.java)

    @Provides
    fun provideUserService(retrofit: Retrofit): UserService =
        retrofit.create(UserService::class.java)

    @Provides
    fun provideCommentsService(retrofit: Retrofit): CommentService =
        retrofit.create(CommentService::class.java)
}

