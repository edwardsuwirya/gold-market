package com.enigmacamp.goldmarket

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        var handler: Handler = Handler()
        handler.postDelayed(Runnable {
            kotlin.run {

                var intent: Intent = Intent( this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }, 3000)
    }
}