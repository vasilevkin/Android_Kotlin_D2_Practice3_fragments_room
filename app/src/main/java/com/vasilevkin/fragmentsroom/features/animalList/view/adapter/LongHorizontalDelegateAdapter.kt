package com.vasilevkin.fragmentsroom.features.animalList.view.adapter

import com.vasilevkin.fragmentsroom.R
import com.vasilevkin.fragmentsroom.delegateadapter.KDelegateAdapter
import com.vasilevkin.fragmentsroom.features.animalList.view.ui.AnimalListFragment
import com.vasilevkin.fragmentsroom.models.localModels.Animal
import com.vasilevkin.fragmentsroom.models.localModels.LongHorizontalCatLocalModel
import com.vasilevkin.fragmentsroom.utils.downloadImageInView
import kotlinx.android.synthetic.main.long_horizontal_item.*
import kotlinx.android.synthetic.main.long_horizontal_item.view.*


class LongHorizontalDelegateAdapter : KDelegateAdapter<LongHorizontalCatLocalModel>() {

    override fun onBind(item: LongHorizontalCatLocalModel, viewHolder: KViewHolder) =
        with(viewHolder) {
            title_text_view.text = item.animal.title
            subtitle_text_view.text = item.animal.subtitle
            downloadImageInView(item.context, itemView.details_image, item.animal.imageUrl.orEmpty())
            itemView.setOnClickListener {
                val act = item.context as AnimalListFragment.OnAnimalSelected
                act.onSelected(Animal(item.animal.title))
            }
        }

    override fun isForViewType(items: List<*>, position: Int) =
        items[position] is LongHorizontalCatLocalModel

    override val layoutId: Int = R.layout.long_horizontal_item
}
