package com.enigmacamp.goldmarket.ui.main.view.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import com.enigmacamp.goldmarket.R
import com.enigmacamp.goldmarket.data.model.Customer
import com.enigmacamp.goldmarket.data.model.CustomerBalance
import com.enigmacamp.goldmarket.ui.LoadingDialog
import com.enigmacamp.goldmarket.ui.base.AppBaseActivity
import com.enigmacamp.goldmarket.ui.main.viewmodel.SignInViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SignInActivity : AppBaseActivity() {
    lateinit var signInButton: Button
    lateinit var signUpButton: Button
    lateinit var loadingDialog: AlertDialog

    lateinit var userEmailTextInput: TextInputLayout
    lateinit var userPasswordTextInput: TextInputLayout

    lateinit var viewModel: SignInViewModel

    companion object {
        const val INTENT_AUTH_CUSTOMER_KEY = "auth_customer"
        const val INTENT_CUSTOMER_BALANCE = "balance_customer"
    }

    private fun initUi() {
        loadingDialog = LoadingDialog.build(this)
        userEmailTextInput = findViewById(R.id.email_textField)
        userPasswordTextInput = findViewById(R.id.password_textField)
        signInButton = findViewById(R.id.login_button)
        signUpButton = findViewById(R.id.signup_button)
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(SignInViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        initUi()
        initViewModel()

        signInButton.setOnClickListener {
            clearKeyboard(findViewById<ConstraintLayout>(R.id.sign_in_layout))
            loadingDialog.show()

            GlobalScope.launch {
                delay(1000)
                val email = userEmailTextInput.editText?.text
                val password = userPasswordTextInput.editText?.text
                viewModel.userAuth.userName = email.toString()
                viewModel.userAuth.userPassword = password.toString()
                val (authCustomer, customerBalance) = viewModel.userAuthValidate()
                loadingDialog.dismiss()
                if (authCustomer != null) {
                    onStartWelcomeActivity(authCustomer, customerBalance)
                } else {
                    viewModel.userAuth.userName = ""
                    viewModel.userAuth.userPassword = ""
                    showSnackBar()
                }

            }
        }

        signUpButton.setOnClickListener {
            onStartSignUpActivity()
        }
    }

    private fun showSnackBar() {
        val snackbar =
            Snackbar.make(findViewById(R.id.sign_in_layout), "Invalid User", Snackbar.LENGTH_LONG)
        val snackbarView = snackbar.view
        snackbarView.background = getDrawable(R.drawable.background_with_radius)
        val textView =
            snackbarView.findViewById(R.id.snackbar_text) as TextView
        textView.setTextColor(getColor(R.color.colorPrimaryDark))
        textView.textSize = 18f
        snackbar.show()
    }

    fun onStartSignUpActivity() {
        finish()
    }

    fun onStartWelcomeActivity(customer: Customer?, customerBalance: CustomerBalance?) {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        intent.putExtra(INTENT_AUTH_CUSTOMER_KEY, customer)
        intent.putExtra(INTENT_CUSTOMER_BALANCE, customerBalance)
        startActivity(intent)
        finish()
    }
}