package com.vasilevkin.fragmentsroom.di

import android.content.Context
import com.vasilevkin.fragmentsroom.features.animalList.view.ui.AnimalListFragment
import com.vasilevkin.fragmentsroom.features.dogdetails.DogDetailsFragment
import com.vasilevkin.fragmentsroom.features.photozoom.PhotoZoomFragment
import com.vasilevkin.fragmentsroom.features.splash.view.ui.SplashActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


@ExperimentalStdlibApi
@Singleton
@Component(modules = [AppModule::class, RepoModule::class])
interface AppComponent {

    // Factory to create instances of the AppComponent
    @Component.Factory
    interface Factory {
        // With @BindsInstance, the Context passed in will be available in the graph
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(activity: SplashActivity)

    fun inject(fragment: AnimalListFragment)
    fun inject(fragment: DogDetailsFragment)
    fun inject(fragment: PhotoZoomFragment)
}
