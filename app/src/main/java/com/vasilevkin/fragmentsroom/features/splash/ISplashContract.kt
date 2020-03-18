package com.vasilevkin.fragmentsroom.features.splash

import com.vasilevkin.fragmentsroom.base.BaseContract

interface ISplashContract {

    interface Presenter : BaseContract.Presenter {
        fun onViewDestroyed()
    }

    interface View : BaseContract.View {
        fun finishView()
    }
}
