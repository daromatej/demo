package com.matejdev.demo.presentation.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.matejdev.demo.R
import com.matejdev.demo.base.view.binding.BindableAdapter
import com.matejdev.demo.domain.model.CommentModel

/**
 * [RecyclerView] adapter for [com.matejdev.demo.presentation.view.CommentListFragment]
 */
class CommentListAdapter
constructor(
    private val dataSet: MutableList<CommentModel> = mutableListOf()
) : RecyclerView.Adapter<CommentListAdapter.CommentViewHolder>(),
    BindableAdapter<List<CommentModel>> {

    override fun setData(data: List<CommentModel>?) {
        dataSet.clear()
        data?.let { dataSet.addAll(it) }
        this.notifyDataSetChanged()
    }

    var itemClickListener: ItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CommentViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_comment, parent, false)
    )

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.name.text = dataSet[position].name
        holder.email.text = dataSet[position].email
        holder.body.text = dataSet[position].body
    }

    override fun getItemCount() = dataSet.size

    inner class CommentViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val name: TextView = item.findViewById(R.id.itemCommentName)
        val email: TextView = item.findViewById(R.id.itemCommentEmail)
        val body: TextView = item.findViewById(R.id.itemCommentBody)

        init {
            itemView.setOnClickListener { itemClickListener?.onItemClick(it, dataSet[adapterPosition]) }
        }
    }

    interface ItemClickListener {
        fun onItemClick(view: View, comment: CommentModel)
    }
}
