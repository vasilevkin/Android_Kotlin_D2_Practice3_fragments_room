package com.vasilevkin.fragmentsroom.delegateadapter.diff

import androidx.recyclerview.widget.DiffUtil


class DiffUtilCallback(
    private val oldList: List<IComparableItem?>,
    private val newList: List<IComparableItem?>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition]?.id() == newList[newItemPosition]?.id()
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition]?.content() == newList[newItemPosition]?.content()
    }

}