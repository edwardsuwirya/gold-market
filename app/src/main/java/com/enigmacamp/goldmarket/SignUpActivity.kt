package com.enigmacamp.goldmarket

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class SignUpActivity : AppCompatActivity() {
    lateinit var signInButton: Button
    lateinit var signUpButton: Button
    lateinit var loadingDialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        loadingDialog = LoadingDialog.build(this)
        signInButton = findViewById(R.id.signin_button)
        signInButton.setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
            finish()
        }
        signUpButton = findViewById(R.id.new_signup_button)
        signUpButton.setOnClickListener {
            loadingDialog.show()
        }
    }
}