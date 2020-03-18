package com.vasilevkin.fragmentsroom.features.animalList.view.adapter

import com.vasilevkin.fragmentsroom.R
import com.vasilevkin.fragmentsroom.delegateadapter.KDelegateAdapter
import com.vasilevkin.fragmentsroom.models.localModels.SquareCatLocalModel
import com.vasilevkin.fragmentsroom.utils.downloadImageInView
import kotlinx.android.synthetic.main.square_item.*
import kotlinx.android.synthetic.main.square_item.view.*


class SquareDelegateAdapter : KDelegateAdapter<SquareCatLocalModel>() {

    override fun onBind(item: SquareCatLocalModel, viewHolder: KViewHolder) =
        with(viewHolder) {
            title_text_view.text = item.title
            subtitle_text_view.text = item.subtitle
            downloadImageInView(item.context, itemView.details_image, item.imageUrl)
        }

    override fun isForViewType(items: List<*>, position: Int) =
        items[position] is SquareCatLocalModel

    override val layoutId: Int = R.layout.square_item
}
