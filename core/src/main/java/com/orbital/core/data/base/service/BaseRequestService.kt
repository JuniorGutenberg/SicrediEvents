package com.orbital.core.data.base.service

import com.orbital.core.utils.NetworkUtils
import retrofit2.Retrofit

open class BaseRequestService {
    protected val remote: Retrofit = NetworkUtils.getRetrofit()
}