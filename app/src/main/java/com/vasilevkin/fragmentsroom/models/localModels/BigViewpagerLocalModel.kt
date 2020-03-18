package com.vasilevkin.fragmentsroom.models.localModels

import com.vasilevkin.fragmentsroom.delegateadapter.diff.IComparableItem
import com.vasilevkin.fragmentsroom.features.animalList.IMainContract


class BigViewpagerLocalModel(
    val context: IMainContract.View?,
    val animals: ArrayList<Animal>
) : IComparableItem {
    override fun id(): Any = animals.first().title.toString()
    override fun content(): Any =
        animals.first().title.toString() + animals.first().subtitle.toString() + animals.first().imageUrl.toString()
}
