package com.vasilevkin.fragmentsroom.features.animalList.view.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.vasilevkin.fragmentsroom.delegateadapter.diff.IComparableItem
import com.vasilevkin.fragmentsroom.models.localModels.Animal
import com.vasilevkin.fragmentsroom.models.localModels.BigViewpagerLocalModel
import com.vasilevkin.fragmentsroom.models.localModels.LongHorizontalCatLocalModel
import com.vasilevkin.fragmentsroom.models.localModels.SquareCatLocalModel
import com.vasilevkin.fragmentsroom.repository.AnimalRepository
import com.vasilevkin.fragmentsroom.repository.IAnimalRepository
import com.vasilevkin.fragmentsroom.utils.emptyAnimalArraySize
import com.vasilevkin.fragmentsroom.utils.numberOfUniqueItems
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.internal.schedulers.IoScheduler
import io.reactivex.subjects.BehaviorSubject

class AnimalListViewModel : ViewModel() {

    private val animalRepository: IAnimalRepository = AnimalRepository()
    private var disposable: Disposable? = null

    var animalList: BehaviorSubject<List<IComparableItem>> = BehaviorSubject.create()
    lateinit var view: Context


//        private val animals: MutableLiveData<List<IComparableItem>> by lazy {
//            MutableLiveData<List<IComparableItem>>().also {
//                loadAnimals()
//            }
//                .postValue(z)
//        }
//
//        fun getAnimalList(): LiveData<List<IComparableItem>> {
//            return animals
//        }
//
//
//


    fun onViewCreated() {
        generateNewData()
        loadAnimals()
    }

    // Private methods

    private fun loadAnimals() {
        val animals = animalRepository.getAllAnimals()

        this.disposable = animals
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(IoScheduler())
            .subscribe { cats ->
                val objects = ArrayList<IComparableItem>(20)
                for (i in cats.indices) {
                    var item: IComparableItem?

                    when (i % numberOfUniqueItems) {
                        0 -> item = LongHorizontalCatLocalModel(
                            view,
                            Animal(
                                cats[i].title.orEmpty(),
                                cats[i].subtitle.orEmpty(),
                                cats[i].imageUrl.orEmpty()
                            )
                        )
                        1, 2 -> item = SquareCatLocalModel(
                            view,
                            Animal(
                                cats[i].title.orEmpty(),
                                cats[i].subtitle.orEmpty(),
                                cats[i].imageUrl.orEmpty()
                            )
                        )
                        5 -> item = BigViewpagerLocalModel(
                            view, arrayListOf(
                                Animal(
                                    cats[i - 2].title.orEmpty(),
                                    cats[i - 2].subtitle.orEmpty(),
                                    cats[i - 2].imageUrl.orEmpty()
                                ), Animal(
                                    cats[i - 1].title.orEmpty(),
                                    cats[i - 1].subtitle.orEmpty(),
                                    cats[i - 1].imageUrl.orEmpty()
                                ), Animal(
                                    cats[i].title.orEmpty(),
                                    cats[i].subtitle.orEmpty(),
                                    cats[i].imageUrl.orEmpty()
                                )
                            )
                        )
                        else -> item = null
                    }
                    if (item != null) {
                        objects.add(item)
                    }
                }

                animalList.onNext(objects)
            }
    }


    private fun generateNewData() {
        val list = prepareData()

        animalList.onNext(list)
    }

    private fun prepareData(): List<IComparableItem> {
        val objects = ArrayList<IComparableItem>(emptyAnimalArraySize)
        for (i in 0 until emptyAnimalArraySize) {
            val item = SquareCatLocalModel(
                view as Context,
                Animal("Title$i", "Description$i", "")
            )
            objects.add(item)
        }
        return objects
    }
}
