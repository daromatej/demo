package com.matejdev.demo.presentation.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.matejdev.demo.R
import com.matejdev.demo.base.view.binding.BindableAdapter
import com.matejdev.demo.domain.model.PostModel

class PostListAdapter
constructor(
    private val dataSet: MutableList<PostModel> = mutableListOf()
) : RecyclerView.Adapter<PostListAdapter.PostViewHolder>(),
    BindableAdapter<List<PostModel>> {

    override fun setData(data: List<PostModel>?) {
        dataSet.clear()
        data?.let { dataSet.addAll(it) }
        this.notifyDataSetChanged()
    }

    var itemClickListener: ItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PostViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
    )

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.title.text = dataSet[position].title
        holder.body.text = dataSet[position].body
    }

    override fun getItemCount() = dataSet.size

    inner class PostViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val title: TextView = item.findViewById(R.id.itemPostTitle)
        val body: TextView = item.findViewById(R.id.itemPostBody)

        init {
            itemView.setOnClickListener { itemClickListener?.onItemClick(it, dataSet[adapterPosition]) }
        }
    }

    interface ItemClickListener {
        fun onItemClick(view: View, post: PostModel)
    }
}
