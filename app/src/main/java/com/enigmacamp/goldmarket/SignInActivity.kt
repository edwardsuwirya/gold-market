package com.enigmacamp.goldmarket

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class SignInActivity : AppCompatActivity() {
    lateinit var signInButton: Button
    lateinit var signUpButton: Button
    lateinit var loadingDialog: AlertDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        signInButton = findViewById(R.id.login_button)
        signInButton.setOnClickListener {
            loadingDialog.show()
        }
        signUpButton = findViewById(R.id.signup_button)
        signUpButton.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
            finish()
        }
    }
}