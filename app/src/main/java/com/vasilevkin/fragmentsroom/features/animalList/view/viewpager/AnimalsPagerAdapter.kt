package com.vasilevkin.fragmentsroom.features.animalList.view.viewpager

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.vasilevkin.fragmentsroom.R
import com.vasilevkin.fragmentsroom.features.animalList.IMainContract
import com.vasilevkin.fragmentsroom.models.localModels.Animal
import com.vasilevkin.fragmentsroom.utils.downloadImageInView
import kotlinx.android.synthetic.main.fragment_animal.view.*


class AnimalsPagerAdapter(
    private val context: Context,
    private val animals: ArrayList<Animal>
) :
    PagerAdapter() {

    private val tag = "AnimalsPagerAdapter"
    private var layoutInflater = LayoutInflater.from(context as Context)

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return (view == `object`)
    }

    override fun getCount(): Int {
        return animals.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val itemView: View = layoutInflater.inflate(R.layout.fragment_animal, container, false)

        itemView.fragment_title_text_view.text = animals[position].title.orEmpty().capitalize()
        itemView.fragment_subtitle_text_view.text = animals[position].subtitle

        if (animals[position].imageUrl != null) {
            downloadImageInView(
                context, itemView.fragment_details_image,
                animals[position].imageUrl.toString()
            )
        }

        container.addView(itemView)
        return itemView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        try { // Remove the view from the container
            container.removeView(`object` as View)
            unbindDrawables(`object`)
        } catch (e: Exception) {
            Log.w(tag, "destroyItem: failed to destroy item and clear it's used resources", e)
        }
    }

    private fun unbindDrawables(view: View) {
        if (view.background != null) {
            view.background.callback = null
        }
        if (view is ViewGroup) {
            for (i in 0 until view.childCount) {
                unbindDrawables(view.getChildAt(i))
            }
            view.removeAllViews()
        }
    }
}