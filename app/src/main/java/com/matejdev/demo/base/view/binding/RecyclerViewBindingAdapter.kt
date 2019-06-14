package com.matejdev.demo.base.view.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

/**
 * Allows to bind [RecyclerView.Adapter] to [RecyclerView] directly in xml.
 * The bound adapter must implement [BindableAdapter]
 */
@BindingAdapter("data")
fun <T> setRecyclerViewProperties(recyclerView: RecyclerView, data: T) {
    (recyclerView.adapter as? BindableAdapter<T>)?.setData(data)
}

interface BindableAdapter<T> {
    fun setData(data: T?)
}
