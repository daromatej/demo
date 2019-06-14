package com.matejdev.demo.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.matejdev.demo.R
import com.matejdev.demo.base.extensions.baseActivity
import com.matejdev.demo.base.util.LogUtil
import com.matejdev.demo.presentation.navigator.BrowserNavigator
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_error.closeButton
import kotlinx.android.synthetic.main.fragment_error.retryButton
import javax.inject.Inject

class ErrorFragment : DaggerFragment() {

    companion object {
        fun newInstance() = ErrorFragment().apply {
            val args = Bundle()
            this.arguments = args
        }
    }

    @Inject
    lateinit var navigator: BrowserNavigator

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_error, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        retryButton.setOnClickListener { onRetry() }
        closeButton.setOnClickListener { onClose() }
    }

    private fun onRetry() {
        LogUtil.logClick()
        navigator.goBack()
    }

    private fun onClose() {
        LogUtil.logClick()
        baseActivity().finish()
    }

    override fun onResume() {
        super.onResume()
        setUpInitialUiState()
    }

    private fun setUpInitialUiState() {
        baseActivity().showUpButton(false)
        baseActivity().title = getString(R.string.fragment_error_title)
    }
}
