package com.enigmacamp.goldmarket

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
    }

    fun onMulaiClicked(view: View) {
        var intent: Intent = Intent( this, MainActivity::class.java)
        startActivity(intent)
    }
}