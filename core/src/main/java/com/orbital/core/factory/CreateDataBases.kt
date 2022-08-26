package com.orbital.core.factory

import android.content.Context
import kotlin.reflect.full.createInstance

class CreateDataBases {
    companion object{
        private val factories:List<String> = listOf(
            "com.orbital.home.data.common.local.HomeDataBaseFactory"
        )

        fun createInstance(context: Context){
            factories.forEach{
                try {
                    val clazz = Class.forName(it).asSubclass(DynamicDataBaseFactory::class.java).kotlin
                    val factory = clazz.createInstance()
                    factory?.createInstance(context)
                }catch (e:Exception){
                    e.printStackTrace()
                }
            }
        }
    }
}