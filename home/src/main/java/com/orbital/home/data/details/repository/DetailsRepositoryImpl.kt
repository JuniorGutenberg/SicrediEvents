package com.orbital.home.data.details.repository

import com.orbital.core.data.base.repository.BaseRepository
import com.orbital.home.data.details.remote.model.DetailsRequest
import com.orbital.home.data.details.remote.service.DetailsService
import io.reactivex.Single
import io.reactivex.SingleEmitter
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class DetailsRepositoryImpl(private var remoteService:DetailsService):BaseRepository(), DetailsRepository {
    override fun checkIn(body: DetailsRequest.Body): Single<String> {
        return Single.create{
            detailsRemote(body,it)
        }
    }

    private fun detailsRemote(body: DetailsRequest.Body,observer: SingleEmitter<String>){
        remoteService.checkIn(body)
            .subscribeOn(Schedulers.newThread())
            .subscribe(object: SingleObserver<String>{
                override fun onSubscribe(d: Disposable) {

                }

                override fun onSuccess(t: String) {
                    observer.onSuccess(t)
                }

                override fun onError(e: Throwable) {
                    observer.onError(e)
                }
            })
    }
}