package com.orbital.home.ui.events.viewModel

import androidx.lifecycle.LiveData
import com.orbital.core.data.base.DataState
import com.orbital.home.data.events.remote.model.EventsResponse

interface EventsViewModel {
    val events: LiveData<DataState<List<EventsResponse.Body>>>
    fun getEvents()
}