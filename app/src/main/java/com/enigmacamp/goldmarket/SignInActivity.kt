package com.enigmacamp.goldmarket

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SignInActivity : AppCompatActivity() {
    lateinit var signInButton: Button
    lateinit var signUpButton: Button
    lateinit var loadingDialog: AlertDialog

    lateinit var userEmailTextInput: TextInputLayout
    lateinit var userPasswordTextInput: TextInputLayout

    companion object {
        const val INTENT_AUTH_CUSTOMER_KEY = "auth_customer"
        const val INTENT_CUSTOMER_BALANCE = "balance_customer"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        loadingDialog = LoadingDialog.build(this)
        userEmailTextInput = findViewById(R.id.email_textField)
        userPasswordTextInput = findViewById(R.id.password_textField)

        signInButton = findViewById(R.id.login_button)
        signInButton.setOnClickListener {
            loadingDialog.show()
            GlobalScope.launch {
                delay(1000)
                val email = userEmailTextInput.editText?.text
                val password = userPasswordTextInput.editText?.text
                if (email.toString() == "enigma" && password.toString() == "123") {
                    val authCustomer = Customer("123", "Enigma", "Camp", "it@enigmacamp.com")
                    onStartWelcomeActivity(authCustomer)
                }
                loadingDialog.dismiss()
            }
        }
        signUpButton = findViewById(R.id.signup_button)
        signUpButton.setOnClickListener {
            onStartSignUpActivity()
        }
    }

    fun onStartSignUpActivity() {
        startActivity(Intent(this, SignUpActivity::class.java))
        finish()
    }

    fun onStartWelcomeActivity(customer: Customer) {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        intent.putExtra(INTENT_AUTH_CUSTOMER_KEY, customer)
        intent.putExtra(INTENT_CUSTOMER_BALANCE, 13_000_000)
        startActivity(intent)
        finish()
    }
}