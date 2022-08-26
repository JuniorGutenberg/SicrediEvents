package com.orbital.home.data.events.repository

import com.orbital.home.data.events.remote.model.EventsResponse
import io.reactivex.Single

interface EventsRepository {
    fun getEvents():Single<List<EventsResponse.Body>>
}