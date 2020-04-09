package com.vasilevkin.fragmentsroom.models.localModels

import android.content.Context
import com.vasilevkin.fragmentsroom.delegateadapter.diff.IComparableItem


class LongHorizontalCatLocalModel(
    val context: Context,
    val animal: Animal
) : IComparableItem {
    override fun id(): Any = animal.title.toString()
    override fun content(): Any =
        animal.title.toString() + animal.subtitle.toString() + animal.imageUrl.toString()
}
