package com.vasilevkin.fragmentsroom.di

import android.content.Context
import com.vasilevkin.fragmentsroom.features.animalList.view.ui.AnimalListFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    // Factory to create instances of the AppComponent
    @Component.Factory
    interface Factory {
        // With @BindsInstance, the Context passed in will be available in the graph
        fun create(@BindsInstance context: Context): AppComponent
    }

        @ExperimentalStdlibApi
        fun inject(fragment: AnimalListFragment)
}
