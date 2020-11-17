package com.enigmacamp.goldmarket

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout


class SignUpActivity : AppCompatActivity() {
    lateinit var signInButton: Button
    lateinit var signUpButton: Button
    lateinit var loadingDialog: AlertDialog
    lateinit var tcButton: Button

    lateinit var firstNameTextInput: TextInputLayout
    lateinit var lastNameTextInput: TextInputLayout
    lateinit var emailTextInput: TextInputLayout
    lateinit var passwordTextInput: TextInputLayout

    var newCustomer: Customer = Customer()

    companion object {
        private const val ENIGMA_URL = "https://www.enigmacamp.com"
        private const val FIRST_NAME_KEY = "FIRST_NAME_KEY"
        private const val LAST_NAME_KEY = "LAST_NAME_KEY"
        private const val EMAIL_KEY = "EMAIL_KEY"
        private const val PASSWORD_KEY = "PASSWORD_KEY"
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(FIRST_NAME_KEY, newCustomer.firstName);
        outState.putString(LAST_NAME_KEY, newCustomer.lastName);
        outState.putString(EMAIL_KEY, newCustomer.email);
        outState.putString(PASSWORD_KEY, newCustomer.password);
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        firstNameTextInput = findViewById(R.id.first_name_textField)
        lastNameTextInput = findViewById(R.id.last_name_textField)
        emailTextInput = findViewById(R.id.email_textField)
        passwordTextInput = findViewById(R.id.password_textField)


        firstNameTextInput.editText?.addTextChangedListener(
            AppTextWatcher {
                afterChanged = { s -> newCustomer.firstName = s.toString() }
            }
        )
        lastNameTextInput.editText?.addTextChangedListener(
            AppTextWatcher {
                afterChanged = { s -> newCustomer.lastName = s.toString() }
            }
        )
        emailTextInput.editText?.addTextChangedListener(
            AppTextWatcher {
                afterChanged = { s -> newCustomer.email = s.toString() }
            }
        )
        passwordTextInput.editText?.addTextChangedListener(
            AppTextWatcher {
                afterChanged = { s -> newCustomer.password = s.toString() }
            }
        )

        loadingDialog = LoadingDialog.build(this)
        signInButton = findViewById(R.id.signin_button)
        signInButton.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            //Prevent multiple new activity
            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent)
        }
        signUpButton = findViewById(R.id.new_signup_button)
        signUpButton.setOnClickListener {
            loadingDialog.show()
        }
        tcButton = findViewById(R.id.tc_button)
        tcButton.setOnClickListener {
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse(ENIGMA_URL)
            )
            startActivity(intent)
        }
        if (savedInstanceState != null) {
            newCustomer.firstName = savedInstanceState.getString(FIRST_NAME_KEY) ?: ""
            newCustomer.lastName = savedInstanceState.getString(LAST_NAME_KEY) ?: ""
            newCustomer.email = savedInstanceState.getString(EMAIL_KEY) ?: ""
            newCustomer.password = savedInstanceState.getString(PASSWORD_KEY) ?: ""
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
        firstNameTextInput.editText?.setText(newCustomer.firstName)
        lastNameTextInput.editText?.setText(newCustomer.lastName)
        emailTextInput.editText?.setText(newCustomer.email)
        passwordTextInput.editText?.setText(newCustomer.password)
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