package com.orbital.home.data.cache.db

import androidx.room.Dao
import androidx.room.Query
import com.orbital.core.data.base.local.BaseDao
import io.reactivex.Single

@Dao
interface CacheDao:BaseDao<CacheEntity>  {
    @Query("SELECT * FROM cache WHERE id = :id")
    fun getCacheById(id: String): Single<CacheEntity>

    @Query("DELETE FROM cache WHERE id = :id")
    fun deleteById(id: String)
}