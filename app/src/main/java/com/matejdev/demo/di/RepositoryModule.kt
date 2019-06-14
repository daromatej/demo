package com.matejdev.demo.di

import com.matejdev.demo.data.repository.CommentRepositoryImpl
import com.matejdev.demo.data.repository.PostRepositoryImpl
import com.matejdev.demo.data.repository.UserRepositoryImpl
import com.matejdev.demo.domain.repository.CommentRepository
import com.matejdev.demo.domain.repository.PostRepository
import com.matejdev.demo.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ServiceModule::class])
class RepositoryModule {

    @Singleton
    @Provides
    fun providePostRepository(repository: PostRepositoryImpl): PostRepository = repository

    @Singleton
    @Provides
    fun provideCommentRepository(repository: CommentRepositoryImpl): CommentRepository = repository

    @Singleton
    @Provides
    fun provideUserRepository(repository: UserRepositoryImpl): UserRepository = repository
}

