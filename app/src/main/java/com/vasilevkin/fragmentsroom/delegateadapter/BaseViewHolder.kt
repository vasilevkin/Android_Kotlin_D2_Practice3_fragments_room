package com.vasilevkin.fragmentsroom.delegateadapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView


open class BaseViewHolder(parent: View) : RecyclerView.ViewHolder(parent) {

    private var listener: ItemInflateListener? = null

    fun setListener(listener: ItemInflateListener) {
        this.listener = listener
    }

    fun bind(item: Any?) {
        listener?.apply { inflated(item, itemView) }
    }

    interface ItemInflateListener {
        fun inflated(viewType: Any?, view: View?)
    }
}