package com.vasilevkin.fragmentsroom.di

import android.content.Context
import com.vasilevkin.fragmentsroom.MainApplication
import com.vasilevkin.fragmentsroom.features.animalList.view.viewmodel.AnimalListViewModel
import com.vasilevkin.fragmentsroom.models.database.dao.AnimalsDao
import com.vasilevkin.fragmentsroom.repository.AnimalRepository
import com.vasilevkin.fragmentsroom.repository.IAnimalRepository
import com.vasilevkin.fragmentsroom.repository.datasource.CloudDataSource
import com.vasilevkin.fragmentsroom.repository.datasource.ICloudDataSource
import com.vasilevkin.fragmentsroom.repository.datasource.ILocalDataSource
import com.vasilevkin.fragmentsroom.repository.datasource.LocalDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class AppModule {

    // repo module
    @Singleton
    @Provides
    fun provideAnimalsDao(): AnimalsDao {
        return MainApplication.database?.animalsDao()!!
    }

    @Singleton
    @Provides
    fun provideLocalDataSource(dao: AnimalsDao): ILocalDataSource {
        return LocalDataSource(dao)
    }

    @Singleton
    @Provides
    fun provideCloudDataSource(): ICloudDataSource {
        return CloudDataSource()
    }

    @Singleton
    @Provides
    fun provideRepository(localDataSource: ILocalDataSource, cloudDataSource: ICloudDataSource): IAnimalRepository {
        return AnimalRepository(localDataSource, cloudDataSource)
    }
    // ===


    @Provides
    fun provideAnimalListViewModel(repo: IAnimalRepository): AnimalListViewModel {
        return AnimalListViewModel(repo)
    }

}
