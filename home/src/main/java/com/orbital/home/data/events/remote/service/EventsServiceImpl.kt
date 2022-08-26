package com.orbital.home.data.events.remote.service

import com.orbital.core.data.base.service.BaseRequestService
import com.orbital.home.data.events.remote.model.EventsEndPoint
import com.orbital.home.data.events.remote.model.EventsResponse
import io.reactivex.Single

class EventsServiceImpl:BaseRequestService(), EventsService {
    override fun getEvents(): Single<List<EventsResponse.Body>> {
        val endPoint = remote.create(EventsEndPoint::class.java)
        return endPoint.getEvents()
    }
}