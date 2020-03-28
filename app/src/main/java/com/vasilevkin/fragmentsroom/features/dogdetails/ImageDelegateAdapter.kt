package com.vasilevkin.fragmentsroom.features.dogdetails

import com.vasilevkin.fragmentsroom.R
import com.vasilevkin.fragmentsroom.delegateadapter.KDelegateAdapter
import com.vasilevkin.fragmentsroom.models.localModels.ImageLocalModel
import com.vasilevkin.fragmentsroom.utils.downloadImageInView
import kotlinx.android.synthetic.main.dog_details_image_item.view.*


@ExperimentalStdlibApi
class ImageDelegateAdapter : KDelegateAdapter<ImageLocalModel>() {

    override fun onBind(item: ImageLocalModel, viewHolder: KViewHolder) =
        with(viewHolder) {
            downloadImageInView(
                item.context,
                itemView.dog_details_imageview,
                item.animal.imageUrl.orEmpty()
            )
            itemView.setOnClickListener {
                val activity = item.context as DogDetailsFragment.OnPhotoSelected
                activity.onSelectedPhoto(item.animal)
            }
        }

    override fun isForViewType(items: List<*>, position: Int) =
        items[position] is ImageLocalModel

    override val layoutId: Int = R.layout.dog_details_image_item
}
