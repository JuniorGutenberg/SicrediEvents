package com.orbital.home.data.events.remote.model

import io.reactivex.Single
import retrofit2.http.GET


interface EventsEndPoint {
    @GET("events")
    fun getEvents(): Single<List<EventsResponse.Body>>
}