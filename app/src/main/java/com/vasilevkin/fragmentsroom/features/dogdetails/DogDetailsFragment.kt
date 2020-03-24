package com.vasilevkin.fragmentsroom.features.dogdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.vasilevkin.fragmentsroom.R
import com.vasilevkin.fragmentsroom.models.localModels.Animal
import com.vasilevkin.fragmentsroom.utils.ANIMAL_DETAILS_MODEL
import kotlinx.android.synthetic.main.dog_details_fragment.*

class DogDetailsFragment : Fragment() {

    companion object {
        fun newInstance(animalModel: Animal): DogDetailsFragment {
            val args = Bundle()
            args.putSerializable(ANIMAL_DETAILS_MODEL, animalModel)
            val fragment = DogDetailsFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var viewModel: DogDetailsViewModel

    // Fragment Lifecycle methods

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dog_details_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.title = "Dog details"

        viewModel = ViewModelProviders.of(this).get(DogDetailsViewModel::class.java)
        viewModel.animal = arguments?.getSerializable(ANIMAL_DETAILS_MODEL) as Animal
    }

    override fun onStart() {
        super.onStart()

        details_title.text = viewModel.animal?.title.orEmpty()
        details_subtitle.text = viewModel.animal?.subtitle.orEmpty()
    }
}
