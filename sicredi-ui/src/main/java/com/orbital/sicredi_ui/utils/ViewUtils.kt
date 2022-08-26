package com.orbital.sicredi_ui.utils

import android.content.Context
import android.util.Log
import com.orbital.sicredi_ui.components.loading.CustomLoading
import java.lang.Exception

class ViewUtils {
    companion object{
        private var loading: CustomLoading? = null

        fun showLoading(context:Context){
            try {
                hideLoading()
                loading = CustomLoading(context)

                loading?.show()
            }catch (e: Exception){
                Log.e("Error Loading: ", e.message.toString())
            }
        }
        fun hideLoading(){
            try {
                loading?.dismiss()
            } catch (e: Exception) {
                Log.e("Error Dismiss: ", e.message.toString())
            } finally {
                loading = null
            }
        }
    }
}