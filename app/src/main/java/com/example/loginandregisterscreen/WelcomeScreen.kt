package com.example.loginandregisterscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class WelcomeScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome_screen)
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this,LoginScreenActivity::class.java))
            finish()
        },3000)

    }
}