package com.vasilevkin.fragmentsroom.features.splash.presenter

import com.vasilevkin.fragmentsroom.base.BasePresenter
import com.vasilevkin.fragmentsroom.features.splash.ISplashContract


class SplashPresenter : BasePresenter<ISplashContract.View>(), ISplashContract.Presenter {

    override fun onViewCreated() {
        view?.finishView()
    }

    override fun onViewDestroyed() {
        view = null
    }
}
