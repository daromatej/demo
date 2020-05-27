package com.matejdev.demo.a.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.matejdev.demo.R
import com.matejdev.demo.a.presentation.navigation.MainNavigator
import kotlinx.android.synthetic.main.welcome_fragment.showToDo
import kotlinx.android.synthetic.main.welcome_fragment.showToDoCount

class WelcomeFragment : Fragment() {

    companion object {
        fun newInstance() = WelcomeFragment().apply {
            val args = Bundle()
            this.arguments = args
        }
    }

    private val navigator by lazy { MainNavigator(requireActivity()) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return layoutInflater.inflate(R.layout.welcome_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setUpButtons()
    }

    private fun setUpButtons() {
        showToDo.setOnClickListener { navigator.showToDoList() }
        showToDoCount.setOnClickListener { navigator.showToDoCount() }
    }
}
