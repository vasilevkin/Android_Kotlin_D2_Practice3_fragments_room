package com.vasilevkin.fragmentsroom.features.photozoom

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.vasilevkin.fragmentsroom.R
import com.vasilevkin.fragmentsroom.models.localModels.Animal
import com.vasilevkin.fragmentsroom.utils.ANIMAL_DETAILS_MODEL
import com.vasilevkin.fragmentsroom.utils.downloadImageInView


class PhotoZoomFragment : Fragment() {

    companion object {
        fun newInstance(animalModel: Animal): PhotoZoomFragment {
            val args = Bundle()
            args.putSerializable(ANIMAL_DETAILS_MODEL, animalModel)
            val fragment = PhotoZoomFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var viewModel: PhotoZoomViewModel
    private var photoZoomImageView: ImageView? = null

    // Fragment Lifecycle methods

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.photo_zoom_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(PhotoZoomViewModel::class.java)
        viewModel.animal = arguments?.getSerializable(ANIMAL_DETAILS_MODEL) as Animal
    }

    override fun onStart() {
        super.onStart()

        photoZoomImageView = view?.findViewById<ImageView>(R.id.photo_zoom_imageview)

        val imageUrl = viewModel.animal?.imageUrl.orEmpty()
        photoZoomImageView?.let {
            downloadImageInView(activity as Context, it, imageUrl)
        }
    }
}
