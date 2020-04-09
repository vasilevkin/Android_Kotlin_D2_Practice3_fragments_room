package com.vasilevkin.fragmentsroom.features.animalList.view.adapter

import com.vasilevkin.fragmentsroom.R
import com.vasilevkin.fragmentsroom.delegateadapter.KDelegateAdapter
import com.vasilevkin.fragmentsroom.features.animalList.view.viewpager.AnimalsPagerAdapter
import com.vasilevkin.fragmentsroom.models.localModels.BigViewpagerLocalModel
import kotlinx.android.synthetic.main.big_viewpager_item.*


@ExperimentalStdlibApi
class BigViewpagerDelegateAdapter : KDelegateAdapter<BigViewpagerLocalModel>() {

    private lateinit var pagerAdapter: AnimalsPagerAdapter

    override fun onBind(item: BigViewpagerLocalModel, viewHolder: KViewHolder) {

        with(viewHolder) {
            pagerAdapter = AnimalsPagerAdapter(item.context, item.animals)
            animal_view_pager.adapter = pagerAdapter
        }
    }

    override fun isForViewType(items: List<*>, position: Int) =
        items[position] is BigViewpagerLocalModel

    override val layoutId: Int = R.layout.big_viewpager_item
}
