package com.orbital.home.data.cache.model

import com.orbital.home.data.cache.db.CacheEntity

object CacheMap {
    fun fromDB(cacheEntity: CacheEntity): CacheModel = CacheModel(cacheEntity.id,cacheEntity.data,cacheEntity.validity)
    fun toDB(cacheModel: CacheModel): CacheEntity = CacheEntity(cacheModel.id,cacheModel.data,cacheModel.validity)
}