package com.orbital.core.factory

import android.content.Context

interface DynamicDataBaseFactory {
    fun createInstance(context:Context)
}