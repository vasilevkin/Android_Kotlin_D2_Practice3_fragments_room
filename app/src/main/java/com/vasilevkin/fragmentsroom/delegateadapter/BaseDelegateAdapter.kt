package com.vasilevkin.fragmentsroom.delegateadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.vasilevkin.fragmentsroom.delegateadapter.diff.IComparableItem


abstract class BaseDelegateAdapter<VH : BaseViewHolder?, T> : IDelegateAdapter<RecyclerView.ViewHolder, IComparableItem> {

    protected abstract fun onBindViewHolder(view: View, item: T, viewHolder: VH)

    @get:LayoutRes
    protected abstract val layoutId: Int

    /**
     * @param parent inflated view
     */
    protected abstract fun createViewHolder(parent: View): VH

    override fun onRecycled(holder: RecyclerView.ViewHolder) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflatedView = LayoutInflater.from(parent.context)
            .inflate(layoutId, parent, false)
        val holder = createViewHolder(inflatedView)
        holder!!.setListener ( object : BaseViewHolder.ItemInflateListener {
            override fun inflated(viewType: Any?, view: View?) {
                onBindViewHolder(view ?: return, viewType as T, holder)
            }
        })
        return holder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, items: List<IComparableItem>, position: Int) {
        (holder as BaseViewHolder).bind(items[position])
    }
}