package com.vasilevkin.fragmentsroom.features.photozoom

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.vasilevkin.fragmentsroom.R

class PhotoZoomFragment : Fragment() {

    companion object {
        fun newInstance() = PhotoZoomFragment()
    }

    private lateinit var viewModel: PhotoZoomViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.photo_zoom_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(PhotoZoomViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
