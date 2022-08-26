package com.orbital.core.data.base.local

import androidx.room.Delete
import androidx.room.OnConflictStrategy


interface BaseDao<ENTITY> {
    @androidx.room.Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(entities: List<ENTITY>?)

    @androidx.room.Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: ENTITY): Long

    @Delete
    fun delete(entity: ENTITY)

    @androidx.room.Update
    fun update(entity: ENTITY)
}