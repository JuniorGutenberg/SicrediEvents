package com.orbital.home.ui.details.viewModel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.orbital.core.data.base.DataState
import com.orbital.home.data.details.remote.model.DetailsRequest
import com.orbital.home.data.details.remote.service.DetailsServiceImpl
import com.orbital.home.data.details.repository.DetailsRepository
import com.orbital.home.data.details.repository.DetailsRepositoryImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DetailsViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailsViewModelImpl::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DetailsViewModelImpl(
                DetailsRepositoryImpl(
                    DetailsServiceImpl()
                )
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

internal class DetailsViewModelImpl(private var repository: DetailsRepository):ViewModel(), DetailsViewModel {
    private var _check:MutableLiveData<DataState<String>> = MutableLiveData()
    override val check: LiveData<DataState<String>> = _check

    @SuppressLint("CheckResult")
    override fun checkIn(body: DetailsRequest.Body) {
        repository.checkIn(body)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({callback ->
                _check.postValue(DataState.Success(callback))
            },{error->
                error.printStackTrace()
                _check.postValue(DataState.Error(error))
            })
    }
}