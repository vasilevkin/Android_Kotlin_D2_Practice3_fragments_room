package com.vasilevkin.fragmentsroom.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.vasilevkin.fragmentsroom.R
import com.vasilevkin.fragmentsroom.network.CatInterface
import com.vasilevkin.fragmentsroom.network.DogInterface
import com.vasilevkin.fragmentsroom.network.ServiceGetter


fun getDataServiceCommon(): CatInterface = ServiceGetter.getDataService()

fun getDogDataServiceCommon(): DogInterface = ServiceGetter.getDogDataService()

fun downloadImageInView(context: Context, view: ImageView, url: String) {
    Glide
        .with(context)
        .load(url)
        .placeholder(R.mipmap.ic_launcher)
        .error(R.mipmap.ic_launcher)
        .into(view)
}