package com.orbital.home.ui.details.viewModel

import androidx.lifecycle.LiveData
import com.orbital.core.data.base.DataState
import com.orbital.home.data.details.remote.model.DetailsRequest


interface DetailsViewModel {
    val check:LiveData<DataState<String>>
    fun checkIn(body: DetailsRequest.Body)
}