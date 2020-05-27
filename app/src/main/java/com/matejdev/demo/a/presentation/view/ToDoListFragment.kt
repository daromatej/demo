package com.matejdev.demo.a.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.matejdev.demo.R
import com.matejdev.demo.a.domain.model.ToDoModel
import com.matejdev.demo.a.presentation.view.adapter.ToDoListAdapter
import com.matejdev.demo.a.presentation.viewmodel.ToDoListViewModel
import kotlinx.android.synthetic.main.todo_list_fragment.listTodo
import kotlinx.android.synthetic.main.todo_list_fragment.todoRecycler

class ToDoListFragment : Fragment() {

    companion object {
        fun newInstance() = ToDoListFragment().apply {
            val args = Bundle()
            this.arguments = args
        }
    }

    val model: ToDoListViewModel by viewModels()

    val observer = Observer<List<ToDoModel>> { list ->
        listTodo.text = list.count().toString()
        todoRecycler.adapter = ToDoListAdapter(list)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return layoutInflater.inflate(R.layout.todo_list_fragment, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model.getToDoList().observe(this, observer)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        todoRecycler.let {
            it.layoutManager = LinearLayoutManager(this.activity)
            it.adapter = ToDoListAdapter()//.apply { itemClickListener = this@CommentListFragment }
        }
    }
}
