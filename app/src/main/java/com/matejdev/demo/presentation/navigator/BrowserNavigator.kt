package com.matejdev.demo.presentation.navigator

import androidx.navigation.findNavController
import com.matejdev.demo.R
import com.matejdev.demo.domain.model.PostModel
import com.matejdev.demo.domain.model.UserModel
import com.matejdev.demo.presentation.view.BrowserActivity
import com.matejdev.demo.presentation.view.PostListFragmentDirections
import com.matejdev.demo.presentation.view.UserListFragmentDirections
import javax.inject.Inject

/**
 * Navigation scenarios for [BrowserActivity]
 */
class BrowserNavigator
@Inject
constructor(
    private val activity: BrowserActivity
) {

    fun showPosts(user: UserModel) = navController().navigate(
        UserListFragmentDirections.actionUserListFragmentToPostListFragment(user)
    )

    fun showComments(post: PostModel) = navController().navigate(
        PostListFragmentDirections.actionPostListFragmentToCommentListFragment(post)
    )

    fun showError() = navController().navigate(R.id.errorFragment)

    fun goBack() = navController().popBackStack()

    private fun navController() = activity.findNavController(R.id.navHostFragment)
}
