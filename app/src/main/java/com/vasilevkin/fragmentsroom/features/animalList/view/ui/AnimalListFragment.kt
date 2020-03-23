package com.vasilevkin.fragmentsroom.features.animalList.view.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vasilevkin.fragmentsroom.R
import com.vasilevkin.fragmentsroom.delegateadapter.diff.DiffUtilCompositeAdapter
import com.vasilevkin.fragmentsroom.delegateadapter.diff.IComparableItem
import com.vasilevkin.fragmentsroom.features.animalList.view.viewmodel.AnimalListViewModel
import com.vasilevkin.fragmentsroom.features.animalList.view.adapter.BigViewpagerDelegateAdapter
import com.vasilevkin.fragmentsroom.features.animalList.view.adapter.LongHorizontalDelegateAdapter
import com.vasilevkin.fragmentsroom.features.animalList.view.adapter.SquareDelegateAdapter
import com.vasilevkin.fragmentsroom.models.localModels.Animal
import com.vasilevkin.fragmentsroom.utils.NUMBER_OF_ITEMS_IN_RECYCLER
import io.reactivex.disposables.Disposable


class AnimalListFragment : Fragment() {

    companion object {
        fun newInstance() =
            AnimalListFragment()
    }

    interface OnAnimalSelected {
        fun onSelected(animal: Animal)
    }

    private lateinit var viewModel: AnimalListViewModel
    private var cats: List<IComparableItem> = emptyList()
    private lateinit var diffAdapter: DiffUtilCompositeAdapter
    private var catList: RecyclerView? = null
    private var disposable: Disposable? = null

    private lateinit var listener: OnAnimalSelected

    // Fragment Lifecycle methods

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is OnAnimalSelected) {
            listener = context
        } else {
            throw ClassCastException(
                "$context must implement OnAnimalSelected."
            )
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.animal_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AnimalListViewModel::class.java)
        viewModel.view = activity as Context
    }

    override fun onStart() {
        super.onStart()
        catList = view?.findViewById<RecyclerView>(R.id.cat_list_recyclerview)

        viewModel.onViewCreated()

        diffAdapter = DiffUtilCompositeAdapter.Builder()
            .add(LongHorizontalDelegateAdapter())
            .add(SquareDelegateAdapter())
            .add(BigViewpagerDelegateAdapter())
            .build()

        catList?.run {
            layoutManager = LinearLayoutManager(activity)
            adapter = diffAdapter
        }

        val manager = GridLayoutManager(activity, 2)
        manager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when (position % NUMBER_OF_ITEMS_IN_RECYCLER) {
                    0 -> 2
                    1, 2 -> 1
                    else -> 2
                }
            }
        }
        catList?.layoutManager = manager

        this.disposable = viewModel.animalList.subscribe {
            displayAnimals(it)
        }
    }

    override fun onStop() {
        super.onStop()
        this.disposable?.dispose()
    }

    // Private methods

    private fun displayAnimals(list: List<IComparableItem>) {
        this.cats = list

        diffAdapter.swapData(cats)
        catList?.scrollToPosition(0)
    }
}
