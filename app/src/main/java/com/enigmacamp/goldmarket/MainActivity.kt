package com.enigmacamp.goldmarket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private var name = ""
    private var gold_amount = 0

    lateinit var authCustomer: Customer

    val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        authCustomer = intent.getParcelableExtra(SignInActivity.INTENT_AUTH_CUSTOMER_KEY)
        name = "${authCustomer.firstName} ${authCustomer.lastName}"
        Log.d(TAG, name)

        gold_amount = intent.getParcelableExtra(SignInActivity.INTENT_CUSTOMER_BALANCE)
        Log.d(TAG, gold_amount.toString())
    }
}