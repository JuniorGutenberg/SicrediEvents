package com.orbital.home.data.details.remote.service

import com.orbital.home.data.details.remote.model.DetailsRequest
import io.reactivex.Single

interface DetailsService {
    fun checkIn(body:DetailsRequest.Body):Single<String>
}