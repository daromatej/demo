package com.matejdev.demo.base.view

import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

/**
 * Base injectable activity with viewModel
 */
abstract class ViewModelActivity<VM : BaseViewModel> : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    protected lateinit var model: VM

    abstract fun getLayoutResId(): Int

    abstract fun getViewModelClass(): Class<VM>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResId())
        setUpModel()
    }

    private fun setUpModel() {
        model = viewModelFactory.create(getViewModelClass())
        model.init()
    }
}
