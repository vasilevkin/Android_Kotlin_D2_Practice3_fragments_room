package com.vasilevkin.fragmentsroom.features.animalList.view.adapter

import com.vasilevkin.fragmentsroom.R
import com.vasilevkin.fragmentsroom.delegateadapter.KDelegateAdapter
import com.vasilevkin.fragmentsroom.features.animalList.view.ui.AnimalListFragment
import com.vasilevkin.fragmentsroom.models.localModels.Animal
import com.vasilevkin.fragmentsroom.models.localModels.SquareCatLocalModel
import com.vasilevkin.fragmentsroom.utils.downloadImageInView
import kotlinx.android.synthetic.main.square_item.*
import kotlinx.android.synthetic.main.square_item.view.*


class SquareDelegateAdapter : KDelegateAdapter<SquareCatLocalModel>() {

    override fun onBind(item: SquareCatLocalModel, viewHolder: KViewHolder) =
        with(viewHolder) {
            title_text_view.text = item.animal.title.orEmpty().capitalize()
            subtitle_text_view.text = item.animal.subtitle
            downloadImageInView(item.context, itemView.details_image, item.animal.imageUrl.orEmpty())
            itemView.setOnClickListener {
                val act = item.context as AnimalListFragment.OnAnimalSelected
                act.onSelected(Animal(item.animal.title))
            }
        }

    override fun isForViewType(items: List<*>, position: Int) =
        items[position] is SquareCatLocalModel

    override val layoutId: Int = R.layout.square_item
}
