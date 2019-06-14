package com.matejdev.demo.base.view

import android.os.Bundle
import com.matejdev.demo.base.extensions.toVisibleOrGone
import kotlinx.android.synthetic.main.toolbar.toolbar
import kotlinx.android.synthetic.main.toolbar.toolbarLoader

abstract class BaseActivity<VM : BaseViewModel> : ViewModelActivity<VM>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpToolbar()
    }

    private fun setUpToolbar() {
        toolbar?.let {
            setSupportActionBar(it)
            it.setNavigationOnClickListener { onNavigateUp() }
        }
    }

    fun showUpButton(show: Boolean) {
        supportActionBar?.setDisplayHomeAsUpEnabled(show)
    }

    fun showLoader(show: Boolean) {
        toolbarLoader?.visibility = show.toVisibleOrGone()
    }
}
