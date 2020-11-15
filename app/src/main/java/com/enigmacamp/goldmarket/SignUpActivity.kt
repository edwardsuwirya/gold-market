package com.enigmacamp.goldmarket

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class SignUpActivity : AppCompatActivity() {
    lateinit var signInButton: Button
    lateinit var signUpButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        signInButton = findViewById(R.id.signin_button)
        signInButton.setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
            finish()
        }
        signUpButton = findViewById(R.id.new_signup_button)
        signUpButton.setOnClickListener {

        }
    }
}