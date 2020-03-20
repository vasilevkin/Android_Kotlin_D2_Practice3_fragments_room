package com.vasilevkin.fragmentsroom.features.animalList

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.vasilevkin.fragmentsroom.R

class AnimalListFragment : Fragment() {

    companion object {
        fun newInstance() = AnimalListFragment()
    }

    private lateinit var viewModel: AnimalListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.animal_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AnimalListViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
