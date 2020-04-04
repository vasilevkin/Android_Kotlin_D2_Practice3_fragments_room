package com.vasilevkin.fragmentsroom

import android.app.Application
import androidx.room.Room
import com.facebook.stetho.Stetho
import com.vasilevkin.fragmentsroom.di.AppComponent
import com.vasilevkin.fragmentsroom.di.DaggerAppComponent
import com.vasilevkin.fragmentsroom.di.animalListModule
import com.vasilevkin.fragmentsroom.di.splashModule
import com.vasilevkin.fragmentsroom.models.database.AppDatabase
import com.vasilevkin.fragmentsroom.utils.DATABASE_NAME
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


class MainApplication : Application() {

    companion object {
        var database: AppDatabase? = null
    }

    val appComponent: AppComponent by lazy {
        initializeComponent()
    }

    open fun initializeComponent(): AppComponent {
        return DaggerAppComponent.factory().create(applicationContext)
    }

    override fun onCreate() {
        super.onCreate()

        // Start Koin
        startKoin {
            androidContext(this@MainApplication)
            androidLogger()
            modules(listOf(animalListModule, splashModule))
        }

        // Create an InitializerBuilder
        val initializerBuilder = Stetho.newInitializerBuilder(this).also {

            // Enable Chrome DevTools
            it.enableWebKitInspector(
                Stetho.defaultInspectorModulesProvider(this)
            )

            // Enable command line interface
            it.enableDumpapp(
                Stetho.defaultDumperPluginsProvider(this)
            )
        }

        // Use the InitializerBuilder to generate an Initializer
        val initializer = initializerBuilder.build()

        // Initialize Stetho with the Initializer
        Stetho.initialize(initializer)

        // Room database
        database =
            Room.databaseBuilder(this, AppDatabase::class.java, DATABASE_NAME).build()
    }
}