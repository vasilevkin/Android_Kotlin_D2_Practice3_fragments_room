package com.vasilevkin.fragmentsroom.di

import android.content.Context
import com.vasilevkin.fragmentsroom.features.animalList.view.viewmodel.AnimalListViewModel
import com.vasilevkin.fragmentsroom.repository.AnimalRepository
import com.vasilevkin.fragmentsroom.repository.IAnimalRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class AppModule {

    @Singleton
    @Provides
    fun provideRepository(repo: AnimalRepository): IAnimalRepository {
        return AnimalRepository()
    }

    @Provides
    fun provideAnimalListViewModel(repo: IAnimalRepository): AnimalListViewModel {
        return AnimalListViewModel(repo)
    }

}
