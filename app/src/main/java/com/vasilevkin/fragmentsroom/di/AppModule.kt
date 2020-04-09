package com.vasilevkin.fragmentsroom.di

import android.content.Context
import com.vasilevkin.fragmentsroom.MainApplication
import com.vasilevkin.fragmentsroom.features.animalList.view.viewmodel.AnimalListViewModel
import com.vasilevkin.fragmentsroom.features.splash.ISplashContract
import com.vasilevkin.fragmentsroom.features.splash.presenter.SplashPresenter
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

    @Provides
    fun provideAnimalListViewModel(repo: IAnimalRepository): AnimalListViewModel {
        return AnimalListViewModel(repo)
    }

    @Provides
    fun provideISplashContractPresenter(): ISplashContract.Presenter {
        return SplashPresenter()
    }

}
