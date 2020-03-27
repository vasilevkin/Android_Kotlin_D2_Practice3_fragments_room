package com.vasilevkin.fragmentsroom.features.animalList.view.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.vasilevkin.fragmentsroom.R
import com.vasilevkin.fragmentsroom.features.dogdetails.DogDetailsFragment
import com.vasilevkin.fragmentsroom.features.photozoom.PhotoZoomFragment
import com.vasilevkin.fragmentsroom.models.localModels.Animal
import com.vasilevkin.fragmentsroom.utils.TAG_ANIMAL_LIST_FRAGMENT
import com.vasilevkin.fragmentsroom.utils.TAG_DOG_DETAILS_FRAGMENT
import com.vasilevkin.fragmentsroom.utils.TAG_PHOTO_ZOOM_FRAGMENT


class MainActivity : AppCompatActivity(), AnimalListFragment.OnAnimalSelected,
    DogDetailsFragment.OnPhotoSelected {

    // Lifecycle methods

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.addOnBackStackChangedListener {
            val stackHeight = supportFragmentManager.backStackEntryCount
            if (stackHeight > 0) {
                // if we have something on the stack (doesn't include the current shown fragment)
                supportActionBar?.setHomeButtonEnabled(true)
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
            } else {
                supportActionBar?.setDisplayHomeAsUpEnabled(false)
                supportActionBar?.setHomeButtonEnabled(false)
            }
        }

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.root_layout, AnimalListFragment.newInstance(), TAG_ANIMAL_LIST_FRAGMENT)
                .commit()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                supportFragmentManager.popBackStack()
                true
            }
            else -> super.onOptionsItemSelected(item)
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

    // interface AnimalListFragment.OnPhotoSelected

    override fun onSelectedPhoto(animalPhoto: Animal) {
        val photoZoomFragment = PhotoZoomFragment.newInstance(animalPhoto)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.root_layout, photoZoomFragment, TAG_PHOTO_ZOOM_FRAGMENT)
            .addToBackStack(TAG_PHOTO_ZOOM_FRAGMENT)
            .commit()
    }
}
