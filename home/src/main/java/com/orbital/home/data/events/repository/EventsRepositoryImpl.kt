package com.orbital.home.data.events.repository

import android.annotation.SuppressLint
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.orbital.core.data.base.repository.BaseRepository
import com.orbital.home.data.cache.CacheTime
import com.orbital.home.data.cache.local.CacheService
import com.orbital.home.data.cache.model.CacheModel
import com.orbital.home.data.events.remote.model.EventsResponse
import com.orbital.home.data.events.remote.service.EventsService
import io.reactivex.Single
import io.reactivex.SingleEmitter
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.*

class EventsRepositoryImpl(private val remoteService:EventsService,
                            private val localService:CacheService):BaseRepository(), EventsRepository {
    @SuppressLint("CheckResult")
    override fun getEvents(): Single<List<EventsResponse.Body>> {
        return Single.create{ observer ->
            eventsLocal()
                .subscribeOn(Schedulers.newThread())
                .subscribe({cache ->
                    cache.validity?.let {validity->
                        if(validity> Date()){
                            cache.data?.let {
                                convertData(it).let { data ->
                                    observer.onSuccess(data)
                                }
                            }?:observer.onError(Throwable())
                        } else eventsRemote(observer)
                    }?: eventsRemote(observer)
                },{
                    eventsRemote(observer)
                })
        }
    }

    private fun eventsLocal():Single<CacheModel>{
        return localService.getCache(getIdEvents())
    }

    private fun getIdEvents():String{
        return "Events"
    }
    private fun convertData(s: String?): List<EventsResponse.Body> {
        val gson = Gson()
        val listType = object : TypeToken<List<EventsResponse.Body?>?>() {}.type
        return gson.fromJson(s.toString(), listType)
    }

    private fun eventsRemote(observer:SingleEmitter<List<EventsResponse.Body>>){
        remoteService.getEvents()
            .subscribeOn(Schedulers.newThread())
            .subscribe(object: SingleObserver<List<EventsResponse.Body>>{
                override fun onSubscribe(d: Disposable) {

                }

                override fun onSuccess(t: List<EventsResponse.Body>) {
                    saveEventsLocal(t)
                    observer.onSuccess(t)
                }

                override fun onError(e: Throwable) {
                    saveEventsLocal(null)
                    observer.onError(e)
                }
            })
    }

    private fun saveEventsLocal(data: List<EventsResponse.Body>?){
        val id = getIdEvents()
        val dataString = data.let { Gson().toJson(data) }

        val validity = Calendar.getInstance()

        if(data == null){
            validity.add(Calendar.MINUTE, CacheTime.Events.error)
        }else{
            validity.add(Calendar.MINUTE, CacheTime.Events.success)
        }
        val model = CacheModel(id, dataString, validity.time)
        localService.save(model)
    }
}