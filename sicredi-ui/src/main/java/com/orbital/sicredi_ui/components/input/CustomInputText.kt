package com.orbital.sicredi_ui.components.input

import android.content.Context
import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.PorterDuff
import android.os.Build
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.orbital.sicredi_ui.R
import com.orbital.sicredi_ui.databinding.ComponentInputTextBinding

class CustomInputText:ConstraintLayout {

    private lateinit var binding: ComponentInputTextBinding
    private var eyePassword = false


    constructor(context: Context?):super(context!!){
        init()
    }
    constructor(context: Context?, attrs: AttributeSet?) : super(context!!,attrs) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context!!,attrs,defStyleAttr) {
        init()
    }

    private fun init(){
        binding = ComponentInputTextBinding.inflate(LayoutInflater.from(context),this,true)
    }
    fun setTextTilte(text:String){
        binding.tvTitle.text = text
    }
    fun cleanComponents(){
        binding.apply {
            et.setText("")
            removeError()
        }
    }
    fun setError(text:String){
        binding.apply {
            tvError.visibility = View.VISIBLE
            tvError.text = text
            if(!eyePassword) {
                ivIconRight.setImageResource(R.drawable.ic_baseline_error_outline_24)
                ivIconRight.visibility = View.VISIBLE
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                et.background.colorFilter = BlendModeColorFilter(
                    ContextCompat.getColor(context, android.R.color.holo_red_dark),
                    BlendMode.SRC_ATOP)
            } else {
                et.background.setColorFilter(
                    resources.getColor(android.R.color.holo_red_dark),
                    PorterDuff.Mode.SRC_ATOP)
            }
        }
    }
    fun removeError(){
        binding.apply {
            tvError.visibility = View.GONE
            tvError.text = ""
            tvError.visibility = View.GONE
            if(!eyePassword) {
                ivIconRight.visibility = View.GONE
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                et.background.colorFilter = BlendModeColorFilter(
                    ContextCompat.getColor(context, R.color.app_color),
                    BlendMode.SRC_ATOP)
            } else {
                et.background.setColorFilter(
                    resources.getColor(R.color.app_color),
                    PorterDuff.Mode.SRC_ATOP)
            }
        }
    }
    fun setInputType(type:Int){
        binding.et.inputType = type
    }
    fun getEditText(): EditText {
        return binding.et
    }
}