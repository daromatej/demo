package com.matejdev.demo.presentation.view

import com.matejdev.demo.R
import com.matejdev.demo.base.view.BaseActivity
import com.matejdev.demo.databinding.ActivityBrowserBinding
import com.matejdev.demo.presentation.navigator.BrowserNavigator
import com.matejdev.demo.presentation.viewmodel.BrowserViewModel
import javax.inject.Inject

/**
 * Main activity that handles all the users/posts/comments browsing scenarios
 */
class BrowserActivity : BaseActivity<BrowserViewModel, ActivityBrowserBinding>() {

    @Inject
    lateinit var navigator: BrowserNavigator

    override fun getLayoutResId() = R.layout.activity_browser

    override fun getViewModelClass() = BrowserViewModel::class.java

    override fun inflateAndBindModel(): ActivityBrowserBinding =
        inflateView().apply { model = this@BrowserActivity.model }

    override fun onNavigateUp() = navigator.goBack()
}
