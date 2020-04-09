package com.vasilevkin.fragmentsroom.delegateadapter.diff

import android.util.SparseArray
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.vasilevkin.fragmentsroom.delegateadapter.CompositeDelegateAdapter
import com.vasilevkin.fragmentsroom.delegateadapter.IDelegateAdapter


class DiffUtilCompositeAdapter private constructor(typeToAdapterMap: SparseArray<IDelegateAdapter<RecyclerView.ViewHolder, IComparableItem>>) :
    CompositeDelegateAdapter<IComparableItem>(typeToAdapterMap) {

    override fun swapData(data: List<IComparableItem>) {
        val diffCallback = DiffUtilCallback(this.data, data)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.data = data
        diffResult.dispatchUpdatesTo(this)
    }

    class Builder {
        private var count = 0
        private val typeToAdapterMap: SparseArray<IDelegateAdapter<RecyclerView.ViewHolder, IComparableItem>> =
            SparseArray()

        fun add(
            delegateAdapter: IDelegateAdapter<RecyclerView.ViewHolder, IComparableItem>
        ): Builder {
            typeToAdapterMap.put(count++, delegateAdapter)
            return this
        }

        fun build(): DiffUtilCompositeAdapter {
            require(count != 0) { "Register at least one adapter" }
            return DiffUtilCompositeAdapter(typeToAdapterMap)
        }
    }
}