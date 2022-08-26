package com.orbital.core.utils

import android.annotation.SuppressLint
import androidx.core.util.PatternsCompat
import com.orbital.core.enums.FormatEnum
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.SimpleDateFormat
import java.util.*

class FormatUtils {
    companion object {

        @SuppressLint("SimpleDateFormat")
        fun timeToDate(date: String, sFormat: String): String {
            try {
                val sdf = SimpleDateFormat(sFormat)
                val netDate = Date(date.toLong() )
                return sdf.format(netDate)
            } catch (e: Exception) {
                return e.toString()
            }
        }
        fun formatCurrency(value: Double?): String? {
            val dfs = DecimalFormatSymbols()
            dfs.currencySymbol = ""
            dfs.monetaryDecimalSeparator = ','
            dfs.groupingSeparator = '.'
            val df =
                DecimalFormat.getCurrencyInstance(Locale(FormatEnum.Locale.APP_LOCALE.language,
                    FormatEnum.Locale.APP_LOCALE.country)) as DecimalFormat
            df.decimalFormatSymbols = dfs
            return df.format(value)
        }
    }
}