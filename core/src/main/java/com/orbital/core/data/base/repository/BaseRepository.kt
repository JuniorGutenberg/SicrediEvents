package com.orbital.core.data.base.repository

import com.google.gson.Gson
import kotlin.reflect.KType
import kotlin.reflect.jvm.javaType

open class BaseRepository {
    @Suppress("UNCHECKED_CAST")
    fun <S: Any> convertData(data: String, kS: KType): S? {
        return try {
            Gson().fromJson<Any>(data, kS.javaType) as? S?
        } catch (e: Throwable) {
            null
        }
    }
}