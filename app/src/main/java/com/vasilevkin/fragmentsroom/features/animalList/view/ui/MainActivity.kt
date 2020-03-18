package com.vasilevkin.fragmentsroom.features.animalList.view.ui

import android.os.Bundle
import android.widget.ProgressBar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.vasilevkin.fragmentsroom.R
import com.vasilevkin.fragmentsroom.base.BaseActivity
import com.vasilevkin.fragmentsroom.delegateadapter.diff.DiffUtilCompositeAdapter
import com.vasilevkin.fragmentsroom.delegateadapter.diff.IComparableItem
import com.vasilevkin.fragmentsroom.features.animalList.IMainContract
import com.vasilevkin.fragmentsroom.features.animalList.view.adapter.BigViewpagerDelegateAdapter
import com.vasilevkin.fragmentsroom.features.animalList.view.adapter.LongHorizontalDelegateAdapter
import com.vasilevkin.fragmentsroom.features.animalList.view.adapter.SquareDelegateAdapter
import com.vasilevkin.fragmentsroom.models.localModels.SquareCatLocalModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf


class MainActivity : BaseActivity<IMainContract.Presenter>(), IMainContract.View {

    private var cats: List<IComparableItem> = emptyList()
    override val presenter: IMainContract.Presenter by inject { parametersOf(this) }

    private lateinit var diffAdapter: DiffUtilCompositeAdapter

    private val size = 20

    private val numberOfItemsInRecycler = 4

// Lifecycle methods

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter.onViewCreated()

        this.cats = prepareData()

        diffAdapter = DiffUtilCompositeAdapter.Builder()
            .add(LongHorizontalDelegateAdapter())
            .add(SquareDelegateAdapter())
            .add(BigViewpagerDelegateAdapter())
            .build()

        catList.run {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = diffAdapter
            generateNewData()
        }

        val manager = GridLayoutManager(this, 2)
        manager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when (position % numberOfItemsInRecycler) {
                    0 -> 2
                    1, 2 -> 1
                    else -> 2
                }
            }
        }
        catList.layoutManager = manager

    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

// IMainContract methods

    override fun displayAnimals(list: List<IComparableItem>) {
        this.cats = list

        diffAdapter.swapData(cats)
        catList.scrollToPosition(0)
    }

    override fun showError(msg: String) {
        // show error
    }

// Private methods

    private fun generateNewData() {
        diffAdapter.swapData(prepareData())
        catList.scrollToPosition(0)
    }

    private fun prepareData(): List<IComparableItem> {
        val objects = ArrayList<IComparableItem>(size)
        for (i in 0 until size) {
            val item = SquareCatLocalModel(
                this,
                getString(R.string.placeholder_item_title) + "$i",
                getString(R.string.placeholder_item_subtitle) + "$i",
                getString(R.string.placeholder_item_imageurl)
            )
            objects.add(item)
        }
        return objects
    }

    override val progressBar: ProgressBar
        get() = ProgressBar(this)
}
