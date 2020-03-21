package com.vasilevkin.fragmentsroom.features.animalList.view.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vasilevkin.fragmentsroom.R
import com.vasilevkin.fragmentsroom.utils.tagAnimalList


class MainActivity : AppCompatActivity() {

    // Lifecycle methods

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.root_layout, AnimalListFragment.newInstance(), tagAnimalList)
                .commit()
        }
    }
}
