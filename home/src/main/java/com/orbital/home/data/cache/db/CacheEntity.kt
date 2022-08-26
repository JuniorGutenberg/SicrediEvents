package com.orbital.home.data.cache.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "cache")
data class CacheEntity(
    @PrimaryKey val id:String,
    val data:String?,
    val validity: Date?
    )