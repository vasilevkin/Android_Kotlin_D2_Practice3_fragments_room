package com.vasilevkin.fragmentsroom.features.dogdetails

import android.content.Context
import androidx.lifecycle.ViewModel
import com.vasilevkin.fragmentsroom.delegateadapter.diff.IComparableItem
import com.vasilevkin.fragmentsroom.models.localModels.Animal
import com.vasilevkin.fragmentsroom.models.localModels.ImageLocalModel
import com.vasilevkin.fragmentsroom.repository.AnimalRepository
import com.vasilevkin.fragmentsroom.repository.IAnimalRepository
import com.vasilevkin.fragmentsroom.utils.EMPTY_ANIMAL_ARRAY_SIZE
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.internal.schedulers.IoScheduler
import io.reactivex.subjects.BehaviorSubject


class DogDetailsViewModel : ViewModel() {

    var animal: Animal? = null

    var animalRepository: IAnimalRepository = AnimalRepository(
//         LocalDataSource(AnimalsDao()), CloudDataSource()
    )
    private var disposable: Disposable? = null

    var animalsImagesList: BehaviorSubject<List<IComparableItem>> = BehaviorSubject.create()
    lateinit var view: Context


    fun onViewCreated() {
        generateNewData()
        loadAnimals()
    }

    // Private methods

    private fun loadAnimals() {

        val animalTitle = animal?.title ?: "hound"
        val animals = animalRepository.getDogImagesFor(animalTitle)

        this.disposable = animals
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(IoScheduler())
            .subscribe { cats ->
                val objects = ArrayList<IComparableItem>(20)
                for (i in cats.indices) {
                    var item: IComparableItem?

                    item = ImageLocalModel(
                        view,
                        Animal(
                            cats[i].title.orEmpty(),
                            cats[i].subtitle.orEmpty(),
                            cats[i].imageUrl.orEmpty()
                        )
                    )

                    if (item != null) {
                        objects.add(item)
                    }
                }

                animalsImagesList.onNext(objects)
            }
    }


    private fun generateNewData() {
        val list = prepareData()

        animalsImagesList.onNext(list)
    }

    private fun prepareData(): List<IComparableItem> {
        val objects = ArrayList<IComparableItem>(EMPTY_ANIMAL_ARRAY_SIZE)
        for (i in 0 until EMPTY_ANIMAL_ARRAY_SIZE) {
            val item = ImageLocalModel(
                view as Context,
                Animal(
                    "Title$i",
                    "Description$i",
                    "https://images.dog.ceo/breeds/hound-walker/n02089867_1620.jpg"
                )
            )
            objects.add(item)
        }
        return objects
    }
}
