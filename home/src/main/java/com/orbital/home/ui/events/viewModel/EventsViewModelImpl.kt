package com.orbital.home.ui.events.viewModel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.orbital.core.data.base.DataState
import com.orbital.home.data.cache.local.CacheServiceImpl
import com.orbital.home.data.common.local.HomeDataBase
import com.orbital.home.data.events.remote.model.EventsResponse
import com.orbital.home.data.events.remote.service.EventsServiceImpl
import com.orbital.home.data.events.repository.EventsRepository
import com.orbital.home.data.events.repository.EventsRepositoryImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class EventsViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EventsViewModelImpl::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return EventsViewModelImpl(
                EventsRepositoryImpl(
                    EventsServiceImpl(),
                    CacheServiceImpl(HomeDataBase.instance.cacheDao())
                )
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

internal class EventsViewModelImpl(private val repository: EventsRepository):ViewModel(), EventsViewModel {
    private val _events:MutableLiveData<DataState<List<EventsResponse.Body>>> = MutableLiveData()
    override val events: LiveData<DataState<List<EventsResponse.Body>>> = _events


    @SuppressLint("CheckResult")
    override fun getEvents() {
        repository.getEvents()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({callback ->
                _events.postValue(DataState.Success(callback))

            },{error ->
                error.printStackTrace()
                _events.postValue(DataState.Error(error))
            })
    }
}