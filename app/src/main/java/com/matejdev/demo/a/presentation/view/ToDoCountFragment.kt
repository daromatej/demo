package com.matejdev.demo.a.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.matejdev.demo.R
import com.matejdev.demo.a.presentation.viewmodel.ToDoCountViewModel
import kotlinx.android.synthetic.main.todo_count_fragment.todoCount

class ToDoCountFragment : Fragment() {

    companion object {
        fun newInstance() = ToDoCountFragment().apply {
            val args = Bundle()
            this.arguments = args
        }
    }

    val model: ToDoCountViewModel by viewModels()

    private val toDoCountObserver = Observer<String> { count ->
        todoCount.text = count
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return layoutInflater.inflate(R.layout.todo_count_fragment, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model.getToDoCount().observe(this, toDoCountObserver)
    }
}
