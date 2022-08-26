package com.orbital.core.enums

import java.util.*

open class FormatEnum {
    enum class Format(val value:String){
        FORMAT_DATA_BRAZIL("dd/MM/yyyy"),
        CODE_REAL("R$")
    }
    enum class Locale (val language: String, val country: String){
        APP_LOCALE("pt","BR")
    }
}