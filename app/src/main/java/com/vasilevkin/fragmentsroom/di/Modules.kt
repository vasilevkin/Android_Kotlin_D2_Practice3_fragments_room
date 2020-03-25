package com.vasilevkin.fragmentsroom.di

import com.vasilevkin.fragmentsroom.features.splash.ISplashContract
import com.vasilevkin.fragmentsroom.features.splash.presenter.SplashPresenter
import com.vasilevkin.fragmentsroom.repository.datasource.ILocalDataSource
import com.vasilevkin.fragmentsroom.repository.datasource.LocalDataSource
import org.koin.dsl.module


val animalListModule = module {

    // Repository injection
    single<ILocalDataSource> { LocalDataSource(get()) }

//    single<ICloudDataSource> { CloudDataSource() }

    // single instance of IAnimalRepository
//    single<IAnimalRepository> { AnimalRepository(get(), get()) }


    // Room injection
//    single { Room.databaseBuilder(get(), AppDatabase::class.java, "db_name").build() }
//    single { get<AppDatabase>().animalsDao() }


}

val splashModule = module {

    factory<ISplashContract.Presenter> { SplashPresenter() }
}
