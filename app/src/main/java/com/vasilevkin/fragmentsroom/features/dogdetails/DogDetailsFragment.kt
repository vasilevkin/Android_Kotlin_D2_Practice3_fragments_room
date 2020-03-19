package com.vasilevkin.fragmentsroom.features.dogdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.vasilevkin.fragmentsroom.R

class DogDetailsFragment : Fragment() {

    companion object {
        fun newInstance() =
            DogDetailsFragment()
    }

    private lateinit var viewModel: DogDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dog_details_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(DogDetailsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
