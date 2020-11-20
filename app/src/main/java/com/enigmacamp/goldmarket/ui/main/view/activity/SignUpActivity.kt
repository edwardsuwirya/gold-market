package com.enigmacamp.goldmarket.ui.main.view.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.enigmacamp.goldmarket.R
import com.enigmacamp.goldmarket.ui.LoadingDialog
import com.enigmacamp.goldmarket.ui.main.viewmodel.SignUpViewModel
import com.enigmacamp.goldmarket.util.AppTextWatcher
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

    lateinit var viewModel: SignUpViewModel

    companion object {
        private const val ENIGMA_URL = "https://www.enigmacamp.com"
    }

    private fun initUi() {
        firstNameTextInput = findViewById(R.id.first_name_textField)
        lastNameTextInput = findViewById(R.id.last_name_textField)
        emailTextInput = findViewById(R.id.email_textField)
        passwordTextInput = findViewById(R.id.password_textField)
        loadingDialog = LoadingDialog.build(this)
        signInButton = findViewById(R.id.signin_button)
        signUpButton = findViewById(R.id.new_signup_button)
        tcButton = findViewById(R.id.tc_button)
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(SignUpViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        initUi()
        initViewModel()

        firstNameTextInput.editText?.addTextChangedListener(
            AppTextWatcher {
                afterChanged = { s -> viewModel.customer.firstName = s.toString() }
            }
        )
        lastNameTextInput.editText?.addTextChangedListener(
            AppTextWatcher {
                afterChanged = { s -> viewModel.customer.lastName = s.toString() }
            }
        )
        emailTextInput.editText?.addTextChangedListener(
            AppTextWatcher {
                afterChanged = { s -> viewModel.customer.email = s.toString() }
            }
        )
        passwordTextInput.editText?.addTextChangedListener(
            AppTextWatcher {
                afterChanged = { s -> viewModel.customer.password = s.toString() }
            }
        )
        signInButton.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            //Prevent multiple new activity
            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent)
        }

        signUpButton.setOnClickListener {
            loadingDialog.show()
        }

        tcButton.setOnClickListener {
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse(ENIGMA_URL)
            )
            startActivity(intent)
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
        firstNameTextInput.editText?.setText(viewModel.customer.firstName)
        lastNameTextInput.editText?.setText(viewModel.customer.lastName)
        emailTextInput.editText?.setText(viewModel.customer.email)
        passwordTextInput.editText?.setText(viewModel.customer.password)
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