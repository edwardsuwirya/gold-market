package com.enigmacamp.goldmarket

import android.content.Intent
import android.os.Bundle
import android.util.Log
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
        }
        signUpButton = findViewById(R.id.new_signup_button)
        signUpButton.setOnClickListener {
            loadingDialog.show()
        }
        print("onCreate")
    }

//    override fun onBackPressed() {
//        val backIntent = Intent(this, WelcomeActivity::class.java)
//        backIntent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
//        startActivity(backIntent)
//    }

    override fun onStart() {
        super.onStart()
        print("onStart, Object Id : ${this.toString()}")
    }

    override fun onResume() {
        super.onResume()
        print("onResume")
    }

    override fun onPause() {
        super.onPause()
        print("onPause")
    }

    override fun onStop() {
        super.onStop()
        print("onStop")
    }

    override fun onRestart() {
        super.onRestart()
        print("onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        print("onDestroy")
    }

    fun print(msg: String) {
        Log.d("Sign Up Activity State ", msg)
    }
}