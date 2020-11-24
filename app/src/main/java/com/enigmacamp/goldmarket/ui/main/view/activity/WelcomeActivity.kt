package com.enigmacamp.goldmarket.ui.main.view.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.enigmacamp.goldmarket.R
import com.enigmacamp.goldmarket.databinding.ActivityWelcomeBinding


class WelcomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_welcome)

        binding.welcomeButton.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
//            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
            //Prevent multiple new activity
            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent)
        }
        print("onCreate")
    }

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
        Log.d("Welcome Activity State ", msg)
    }
}