package com.orbital.home.data.common.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.orbital.core.data.util.Converters
import com.orbital.home.data.cache.db.CacheDao
import com.orbital.home.data.cache.db.CacheEntity


@Database(entities = [CacheEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class HomeDataBase : RoomDatabase(){
    companion object{
        lateinit var instance:HomeDataBase

        fun createInstance(context: Context){
            instance = Room.databaseBuilder(context,HomeDataBase::class.java,"home")
                .build()
        }
    }
    abstract fun cacheDao():CacheDao
}