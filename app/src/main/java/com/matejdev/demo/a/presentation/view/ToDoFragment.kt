package com.matejdev.demo.a.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.matejdev.demo.R
import com.matejdev.demo.a.domain.model.ToDoModel

class ToDoFragment : Fragment() {

    companion object {
        private const val TODO_KEY = "ToDoModel"

        fun newInstance(toDoModel: ToDoModel) = ToDoFragment().apply {
            val args = Bundle()
            args.putSerializable(TODO_KEY, toDoModel)
            this.arguments = args
        }
    }

    private val toDoModel by lazy { arguments?.get(TODO_KEY) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return layoutInflater.inflate(R.layout.todo_fragment, container, false)
    }
}
