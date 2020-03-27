package com.vasilevkin.fragmentsroom.features.dogdetails

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vasilevkin.fragmentsroom.R
import com.vasilevkin.fragmentsroom.delegateadapter.diff.DiffUtilCompositeAdapter
import com.vasilevkin.fragmentsroom.delegateadapter.diff.IComparableItem
import com.vasilevkin.fragmentsroom.models.localModels.Animal
import com.vasilevkin.fragmentsroom.utils.ANIMAL_DETAILS_MODEL
import io.reactivex.disposables.Disposable

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

    private var dogImages: List<IComparableItem> = emptyList()
    private lateinit var diffAdapter: DiffUtilCompositeAdapter
    private var imagesRecyclerView: RecyclerView? = null
    private var disposable: Disposable? = null

    // Fragment Lifecycle methods

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dog_details_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(DogDetailsViewModel::class.java)
        viewModel.animal = arguments?.getSerializable(ANIMAL_DETAILS_MODEL) as Animal

        viewModel.view = activity as Context

        (activity as AppCompatActivity).supportActionBar?.title = viewModel.animal?.title.orEmpty()
    }

    override fun onStart() {
        super.onStart()
        imagesRecyclerView = view?.findViewById<RecyclerView>(R.id.dog_details_images_recyclerview)

        viewModel.onViewCreated()

        diffAdapter = DiffUtilCompositeAdapter.Builder()
            .add(ImageDelegateAdapter())
            .build()

        imagesRecyclerView?.run {
            layoutManager = LinearLayoutManager(activity)
            adapter = diffAdapter
        }

        val manager = GridLayoutManager(activity, 2)
        manager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return 1
            }
        }
        imagesRecyclerView?.layoutManager = manager

        this.disposable = viewModel.animalsImagesList.subscribe {
            displayAnimals(it)
        }
    }

    override fun onStop() {
        super.onStop()
        this.disposable?.dispose()
    }

    // Private methods

    private fun displayAnimals(list: List<IComparableItem>) {
        this.dogImages = list

        diffAdapter.swapData(dogImages)
        imagesRecyclerView?.scrollToPosition(0)
    }

}
