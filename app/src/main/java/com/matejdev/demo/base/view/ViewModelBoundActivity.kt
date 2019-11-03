package com.matejdev.demo.base.view

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

/**
 * Base injectable activity with viewModel and DataBinding
 */
abstract class ViewModelBoundActivity<VM : BaseViewModel, B : ViewDataBinding> : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    protected lateinit var model: VM

    abstract fun getLayoutResId(): Int

    abstract fun getViewModelClass(): Class<VM>

    abstract fun inflateAndBindModel(): B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inflateAndBindModel().let { it.lifecycleOwner = this }
        setUpModel()
    }

    /**
     * Use this function to inflate layout in implementing class while [inflateAndBindModel]
     */
    protected fun inflateView(): B = DataBindingUtil.setContentView(this, getLayoutResId())

    private fun setUpModel() {
        model = viewModelFactory.create(getViewModelClass())
        model.init()
    }
}
