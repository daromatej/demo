package com.matejdev.demo.base.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class ViewModelFragment<VM : BaseViewModel, B : ViewDataBinding> : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    protected lateinit var model: VM

    abstract fun getLayoutResId(): Int

    abstract fun getViewModelClass(): Class<VM>

    abstract fun inflateAndBindModel(inflater: LayoutInflater, container: ViewGroup?): B

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        setUpModel()
        return inflateAndBindModel(inflater, container).let {
            it.lifecycleOwner = this
            it.root
        }
    }

    protected fun inflateView(inflater: LayoutInflater, container: ViewGroup?): B =
        DataBindingUtil.inflate(inflater, getLayoutResId(), container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        model.init()
    }

    private fun setUpModel() {
        model = viewModelFactory.create(getViewModelClass())
    }
}
