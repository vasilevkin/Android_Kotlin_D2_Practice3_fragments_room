package com.vasilevkin.fragmentsroom.features.animalList

import com.vasilevkin.fragmentsroom.base.BaseContract
import com.vasilevkin.fragmentsroom.delegateadapter.diff.IComparableItem


interface IMainContract {

    interface Presenter : BaseContract.Presenter {
        fun onLoadMoreAnimalsTapped()
        fun onDestroy()
    }

    interface View : BaseContract.View {
        fun displayAnimals(list: List<IComparableItem>)
    }
}