package com.matejdev.demo.presentation.navigator

import com.matejdev.demo.R
import com.matejdev.demo.domain.model.PostModel
import com.matejdev.demo.domain.model.UserModel
import com.matejdev.demo.presentation.view.BrowserActivity
import com.matejdev.demo.presentation.view.CommentListFragment
import com.matejdev.demo.presentation.view.ErrorFragment
import com.matejdev.demo.presentation.view.PostListFragment
import com.matejdev.demo.presentation.view.UserListFragment
import javax.inject.Inject

class BrowserNavigator
@Inject
constructor(
    private val activity: BrowserActivity
) {

    fun showUsers() {
        activity.supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, UserListFragment.newInstance())
            .commit()
    }

    fun showPosts(user: UserModel) {
        activity.supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, PostListFragment.newInstance(user))
            .addToBackStack(null)
            .commit()
    }

    fun showComments(post: PostModel) {
        activity.supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, CommentListFragment.newInstance(post))
            .addToBackStack(null)
            .commit()
    }

    fun showError() {
        activity.supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, ErrorFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }

    fun goBack() = activity.supportFragmentManager.popBackStackImmediate()
}
