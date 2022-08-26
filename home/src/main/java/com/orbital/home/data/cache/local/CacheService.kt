package com.orbital.home.data.cache.local

import com.orbital.home.data.cache.model.CacheModel
import io.reactivex.Single

interface CacheService {
    fun save(cacheModel: CacheModel)
    fun getCache(id:String): Single<CacheModel>
}