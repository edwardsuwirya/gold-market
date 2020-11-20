package com.enigmacamp.goldmarket.ui.main.view.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.enigmacamp.goldmarket.data.model.Customer
import com.enigmacamp.goldmarket.data.model.CustomerBalance
import com.enigmacamp.goldmarket.R
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        GlobalScope.launch {
            delay(2000)
            onStartWelcomeActivity()
        }
    }

    fun onStartWelcomeActivity() {
        startActivity(Intent(this, WelcomeActivity::class.java))
//        val intent = Intent(this, MainActivity::class.java)
//        intent.putExtra(SignInActivity.INTENT_AUTH_CUSTOMER_KEY, Customer())
//        intent.putExtra(SignInActivity.INTENT_CUSTOMER_BALANCE, CustomerBalance())
//        startActivity(intent)
        finish()
    }
}