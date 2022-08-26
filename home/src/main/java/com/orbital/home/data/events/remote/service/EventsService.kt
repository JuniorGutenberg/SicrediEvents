package com.orbital.home.data.events.remote.service

import com.orbital.home.data.events.remote.model.EventsResponse
import io.reactivex.Single

interface EventsService {
    fun getEvents():Single<List<EventsResponse.Body>>
}