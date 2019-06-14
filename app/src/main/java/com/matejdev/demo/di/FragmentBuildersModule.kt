package com.matejdev.demo.di

import com.matejdev.demo.presentation.view.CommentListFragment
import com.matejdev.demo.presentation.view.ErrorFragment
import com.matejdev.demo.presentation.view.PostListFragment
import com.matejdev.demo.presentation.view.UserListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributePostListFragment(): PostListFragment

    @ContributesAndroidInjector
    abstract fun contributeUserListFragment(): UserListFragment

    @ContributesAndroidInjector
    abstract fun contributeCommentListFragment(): CommentListFragment

    @ContributesAndroidInjector
    abstract fun contributeErrorFragment(): ErrorFragment
}
