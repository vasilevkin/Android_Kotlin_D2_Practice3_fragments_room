package com.vasilevkin.fragmentsroom.models.localModels

import android.content.Context
import com.vasilevkin.fragmentsroom.delegateadapter.diff.IComparableItem
import com.vasilevkin.fragmentsroom.features.animalList.IMainContract


class LongHorizontalCatLocalModel(
    val context: Context,
    val title: String,
    val subtitle: String,
    val imageUrl: String
) : IComparableItem {
    override fun id(): Any = title
    override fun content(): Any = title + subtitle + imageUrl
}
