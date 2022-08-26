package com.orbital.home.data.details.remote.service

import com.orbital.core.data.base.service.BaseRequestService
import com.orbital.home.data.details.remote.model.DetailsEndPoint
import com.orbital.home.data.details.remote.model.DetailsRequest
import io.reactivex.Single

class DetailsServiceImpl:BaseRequestService(), DetailsService {
    override fun checkIn(body: DetailsRequest.Body): Single<String> {
        val endPoint = remote.create(DetailsEndPoint::class.java)
        return endPoint.checkIn(body)
    }
}