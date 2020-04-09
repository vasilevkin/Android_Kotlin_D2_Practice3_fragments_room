package com.vasilevkin.fragmentsroom.features.photozoom

import androidx.lifecycle.ViewModel
import com.vasilevkin.fragmentsroom.models.localModels.Animal
import javax.inject.Inject


class PhotoZoomViewModel @Inject constructor() : ViewModel() {

    var animal: Animal? = null

}
