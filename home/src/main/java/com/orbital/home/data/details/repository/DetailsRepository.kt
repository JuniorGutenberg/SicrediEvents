package com.orbital.home.data.details.repository

import com.orbital.home.data.details.remote.model.DetailsRequest
import io.reactivex.Single

interface DetailsRepository {
    fun checkIn(body:DetailsRequest.Body):Single<String>
}