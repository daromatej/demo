package com.matejdev.demo.presentation.view

import android.os.Bundle
import com.matejdev.demo.R
import com.matejdev.demo.base.view.BaseActivity
import com.matejdev.demo.presentation.navigator.BrowserNavigator
import com.matejdev.demo.presentation.viewmodel.BrowserViewModel
import javax.inject.Inject

class BrowserActivity : BaseActivity<BrowserViewModel>() {

    @Inject
    lateinit var navigator: BrowserNavigator

    override fun getLayoutResId() = R.layout.activity_browser

    override fun getViewModelClass() = BrowserViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigator.showUsers()
    }

    override fun onNavigateUp() = navigator.goBack()
}
