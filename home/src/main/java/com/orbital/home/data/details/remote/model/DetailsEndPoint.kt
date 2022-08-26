package com.orbital.home.data.details.remote.model

import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface DetailsEndPoint {
    @POST("checkin")
    fun checkIn(@Body body:DetailsRequest.Body): Single<String>
}