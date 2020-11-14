package com.enigmacamp.goldmarket

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class WelcomeActivity : AppCompatActivity() {
    lateinit var beginButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        beginButton = findViewById(R.id.welcome_button)
        beginButton.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }
}