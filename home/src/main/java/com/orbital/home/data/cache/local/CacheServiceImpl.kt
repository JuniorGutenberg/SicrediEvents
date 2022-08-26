package com.orbital.home.data.cache.local

import com.orbital.core.data.base.model.BaseServiceDao
import com.orbital.home.data.cache.db.CacheDao
import com.orbital.home.data.cache.db.CacheEntity
import com.orbital.home.data.cache.model.CacheMap
import com.orbital.home.data.cache.model.CacheModel
import io.reactivex.Single

class CacheServiceImpl(cacheDao: CacheDao): BaseServiceDao<CacheEntity, CacheDao>(cacheDao), CacheService {
    override fun save(cacheModel: CacheModel) {
        val entity = CacheMap.toDB(cacheModel)
        dao.deleteById(cacheModel.id)
        dao.insert(entity)

    }

    override fun getCache(id: String): Single<CacheModel> {
        return dao.getCacheById(id).map { CacheMap.fromDB(it) }
    }
}