package com.vasilevkin.fragmentsroom.di

import com.vasilevkin.fragmentsroom.features.animalList.IMainContract
import com.vasilevkin.fragmentsroom.features.animalList.presenter.MainPresenter
import com.vasilevkin.fragmentsroom.features.splash.ISplashContract
import com.vasilevkin.fragmentsroom.features.splash.presenter.SplashPresenter
import com.vasilevkin.fragmentsroom.repository.AnimalRepository
import com.vasilevkin.fragmentsroom.repository.IAnimalRepository
import org.koin.dsl.module


val animalListModule = module {

    // single instance of IAnimalRepository
    single<IAnimalRepository> { AnimalRepository() }

    factory<IMainContract.Presenter> { (view: IMainContract.View) ->
        MainPresenter(view, get())
    }
}

val splashModule = module {

    factory<ISplashContract.Presenter> { SplashPresenter() }
}
