package com.matejdev.demo.a.presentation.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.matejdev.demo.R
import com.matejdev.demo.a.domain.model.ToDoModel

class ToDoListAdapter(
    private val dataSet: List<ToDoModel> = mutableListOf()
) : RecyclerView.Adapter<ToDoListAdapter.ToDoViewHolder>() {

    var itemClickListener: ItemClickListener? = null

    inner class ToDoViewHolder(item: View) : RecyclerView.ViewHolder(item) {

        val id: TextView = item.findViewById(R.id.todoId)
        val title: TextView = item.findViewById(R.id.todoTitle)

        init {
            item.setOnClickListener { itemClickListener?.onItemClick(it, dataSet[adapterPosition]) }
        }
    }

    interface ItemClickListener {
        fun onItemClick(view: View, user: ToDoModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ToDoViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_todo, parent, false)
    )

    override fun getItemCount() = dataSet.count()

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        holder.id.text = dataSet[position].id.toString()
        holder.title.text = dataSet[position].title
    }
}
