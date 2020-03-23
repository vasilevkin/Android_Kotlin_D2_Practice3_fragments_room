package com.vasilevkin.fragmentsroom.features.animalList.view.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vasilevkin.fragmentsroom.R
import com.vasilevkin.fragmentsroom.features.dogdetails.DogDetailsFragment
import com.vasilevkin.fragmentsroom.models.localModels.Animal
import com.vasilevkin.fragmentsroom.utils.TAG_ANIMAL_LIST_FRAGMENT
import com.vasilevkin.fragmentsroom.utils.TAG_DOG_DETAILS_FRAGMENT


class MainActivity : AppCompatActivity(), AnimalListFragment.OnAnimalSelected {

    // Lifecycle methods

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.root_layout, AnimalListFragment.newInstance(), TAG_ANIMAL_LIST_FRAGMENT)
                .commit()
        }
    }

    // interface AnimalListFragment.OnAnimalSelected

    override fun onSelected(animal: Animal) {
        val detailsFragment = DogDetailsFragment.newInstance(animal)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.root_layout, detailsFragment, TAG_DOG_DETAILS_FRAGMENT)
            .addToBackStack(TAG_DOG_DETAILS_FRAGMENT)
            .commit()
    }
}
