package com.example.edgefitness.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.edgefitness.R

class SplashActivity : AppCompatActivity() {
    private val SPLASH_DISPLAY_LENGTH = 2000L // 2 seconds

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash)

        // Delayed execution of MainActivity
        Handler().postDelayed({
            // Start MainActivity after the specified time
            val mainIntent = Intent(this, LoginActivity::class.java)
            startActivity(mainIntent)
            finish() // close this activity
        }, SPLASH_DISPLAY_LENGTH)

    }
}