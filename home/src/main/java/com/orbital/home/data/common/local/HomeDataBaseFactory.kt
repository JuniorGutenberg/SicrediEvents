package com.orbital.home.data.common.local

import android.content.Context
import com.orbital.core.factory.DynamicDataBaseFactory

class HomeDataBaseFactory:DynamicDataBaseFactory {
    override fun createInstance(context: Context) {
        HomeDataBase.createInstance(context)
    }
}