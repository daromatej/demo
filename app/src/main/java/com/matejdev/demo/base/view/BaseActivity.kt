package com.matejdev.demo.base.view

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import com.matejdev.demo.base.extensions.toVisibleOrGone
import kotlinx.android.synthetic.main.toolbar.toolbar
import kotlinx.android.synthetic.main.toolbar.toolbarLoader

/**
 * Base activity witch common UI logic
 */
abstract class BaseActivity<VM : BaseViewModel, B : ViewDataBinding> : ViewModelBoundActivity<VM, B>() {

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
