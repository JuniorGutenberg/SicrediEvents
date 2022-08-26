package com.orbital.sicredi_ui.components.loading

import android.app.Dialog
import android.content.Context
import com.orbital.sicredi_ui.R

class CustomLoading (context: Context): Dialog(context, R.style.SicrediEvents_Dialog) {
    init {
        init()
    }

    private fun init() {
        setContentView(R.layout.component_loading)
        setCancelable(false)
        setCanceledOnTouchOutside(false)
    }
}