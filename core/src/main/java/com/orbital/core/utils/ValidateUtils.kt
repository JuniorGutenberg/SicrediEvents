package com.orbital.core.utils

import androidx.core.util.PatternsCompat

class ValidateUtils {
    companion object{
        fun isValidateName(s:String, number:Int):Boolean{
            return s.isNotEmpty() && s.length <= number
        }
        fun isValidateEmail(s:String):Boolean{
            return s.isNotEmpty() && PatternsCompat.EMAIL_ADDRESS.matcher(s).matches()
        }
    }
}