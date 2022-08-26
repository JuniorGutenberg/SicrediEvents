package com.orbital.sicredievents.ui.view.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.orbital.core.activity.BaseActivity
import com.orbital.sicredievents.R
import com.orbital.sicredievents.databinding.ActivitySplashBinding
import com.orbital.sicredievents.ui.enums.CountEnum

@SuppressLint("CustomSplashScreen")
class SplashActivity:BaseActivity() {

    private lateinit var binding:ActivitySplashBinding
    private lateinit var animationTop: Animation
    private lateinit var animationBottom: Animation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBinding()
        setAnimation()
        countDown()

    }
    private fun setBinding(){
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
    private fun setAnimation(){
        animationTop = AnimationUtils.loadAnimation(this, R.anim.splash_top_to_bottom)
        animationBottom = AnimationUtils.loadAnimation(this, R.anim.splash_bottom_to_top)

        binding.apply {
            iv.animation = animationTop
            tvBaseboard.animation = animationBottom
            tvTitle.animation = animationBottom
        }
    }
    private fun countDown(){
        val timer = object: CountDownTimer(CountEnum.SECONDS.value, CountEnum.INTERVAL.value) {
            override fun onTick(millisUntilFinished: Long) {}

            override fun onFinish() {
                finishSplash()
            }
        }
        timer.start()
    }
    private fun finishSplash(){
        startActivity(Intent(this,MainActivity::class.java))
        finish()
    }
}