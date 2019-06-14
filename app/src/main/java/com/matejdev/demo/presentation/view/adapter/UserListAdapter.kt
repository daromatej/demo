package com.matejdev.demo.presentation.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.matejdev.demo.R
import com.matejdev.demo.base.view.binding.BindableAdapter
import com.matejdev.demo.domain.model.UserModel

class UserListAdapter
constructor(
    private val dataSet: MutableList<UserModel> = mutableListOf()
) : RecyclerView.Adapter<UserListAdapter.UserViewHolder>(),
    BindableAdapter<List<UserModel>> {

    override fun setData(data: List<UserModel>?) {
        dataSet.clear()
        data?.let { dataSet.addAll(it) }
        this.notifyDataSetChanged()
    }

    var itemClickListener: ItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = UserViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
    )

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.name.text = dataSet[position].name
        holder.email.text = dataSet[position].email
    }

    override fun getItemCount() = dataSet.size

    inner class UserViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val name: TextView = item.findViewById(R.id.itemUserName)
        val email: TextView = item.findViewById(R.id.itemUserEmail)

        init {
            itemView.setOnClickListener { itemClickListener?.onItemClick(it, dataSet[adapterPosition]) }
        }
    }

    interface ItemClickListener {
        fun onItemClick(view: View, user: UserModel)
    }
}
