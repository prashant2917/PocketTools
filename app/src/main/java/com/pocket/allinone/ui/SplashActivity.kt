package com.pocket.allinone.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.pocket.allinone.R

class SplashActivity : AppCompatActivity() {
    private val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }

    override fun onResume() {
        super.onResume()
        handler.postDelayed(runnable, SPLASH_SCREEN_TIME)
    }

    override fun onStop() {
        super.onStop()
        handler.removeCallbacks(runnable)
    }


    private val runnable = Runnable {
        startActivity(Intent(this, MainActivity::class.java))
        finish()


    }

    companion object {
        const val SPLASH_SCREEN_TIME = 3 * 1000L
    }
}