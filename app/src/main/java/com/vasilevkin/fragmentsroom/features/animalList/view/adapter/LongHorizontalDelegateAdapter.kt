package com.vasilevkin.fragmentsroom.features.animalList.view.adapter

import com.vasilevkin.fragmentsroom.R
import com.vasilevkin.fragmentsroom.delegateadapter.KDelegateAdapter
import com.vasilevkin.fragmentsroom.utils.downloadImageInView
import com.vasilevkin.fragmentsroom.models.localModels.LongHorizontalCatLocalModel
import kotlinx.android.synthetic.main.long_horizontal_item.*
import kotlinx.android.synthetic.main.long_horizontal_item.view.*


class LongHorizontalDelegateAdapter : KDelegateAdapter<LongHorizontalCatLocalModel>() {

    override fun onBind(item: LongHorizontalCatLocalModel, viewHolder: KViewHolder) =
        with(viewHolder) {
            title_text_view.text = item.title
            subtitle_text_view.text = item.subtitle
            downloadImageInView(item.context, itemView.details_image, item.imageUrl)
        }

    override fun isForViewType(items: List<*>, position: Int) =
        items[position] is LongHorizontalCatLocalModel

    override val layoutId: Int = R.layout.long_horizontal_item
}
