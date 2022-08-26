package com.orbital.sicredievents.ui.view.activity

import android.os.Bundle
import com.orbital.core.activity.BaseActivity
import com.orbital.core.factory.CreateDataBases
import com.orbital.sicredievents.R

class MainActivity:BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        CreateDataBases.Companion.createInstance(this)
    }
}