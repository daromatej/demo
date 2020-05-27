package com.matejdev.demo.a.presentation.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.matejdev.demo.R
import com.matejdev.demo.base.extensions.toVisibleOrGone
import kotlinx.android.synthetic.main.toolbar.toolbar
import kotlinx.android.synthetic.main.toolbar.toolbarLoader

class MainActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpToolbar()
    }

    private fun setUpToolbar() {
        toolbar?.let {
            setSupportActionBar(it)
            it.setNavigationOnClickListener { findNavController(R.id.navHostFragment).navigateUp() }
        }
    }

    fun showUpButton(show: Boolean) {
        supportActionBar?.setDisplayHomeAsUpEnabled(show)
    }

    fun showLoader(show: Boolean) {
        toolbarLoader?.visibility = show.toVisibleOrGone()
    }
}
