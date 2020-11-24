package com.enigmacamp.goldmarket.ui.main.view.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.enigmacamp.goldmarket.R
import com.enigmacamp.goldmarket.data.model.AppState
import com.enigmacamp.goldmarket.data.model.Customer
import com.enigmacamp.goldmarket.databinding.ActivitySignInBinding
import com.enigmacamp.goldmarket.ui.LoadingDialog
import com.enigmacamp.goldmarket.ui.base.AppBaseActivity
import com.enigmacamp.goldmarket.ui.main.viewmodel.SignInViewModel
import com.enigmacamp.goldmarket.ui.main.viewmodel.SignInViewModelInjector
import com.google.android.material.snackbar.Snackbar

class SignInActivity : AppBaseActivity() {
    private val TAG = SignInActivity::class.qualifiedName
    private lateinit var binding: ActivitySignInBinding
    lateinit var loadingDialog: AlertDialog
    lateinit var viewModel: SignInViewModel

    companion object {
        const val INTENT_AUTH_CUSTOMER_KEY = "auth_customer"
    }

    private fun initUi() {
        loadingDialog = LoadingDialog.build(this)
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(
            this,
            SignInViewModelInjector.getFactory()
        ).get(SignInViewModel::class.java)
    }

    private fun subscribe() {
        viewModel.response.observe(this, {
            when (it) {
                is AppState.Loading -> {
                    loadingDialog.show()
                }
                is AppState.Success -> {
                    loadingDialog.dismiss()
                    it.data?.let {
                        val authCustomer = it
                        onStartWelcomeActivity(authCustomer)
                    }
                }
                is AppState.Error -> {
                    loadingDialog.dismiss()
                    showSnackBar()
                }
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_in)
        initUi()
        initViewModel()
        subscribe()
        binding.lifecycleOwner = this
        binding.apply {
            signInViewModel = viewModel
            loginButton.setOnClickListener {
                onSubmitLogin()
            }
            signupButton.setOnClickListener {
                onStartSignUpActivity()
            }
        }
    }

    private fun onSubmitLogin() {
        clearKeyboard(binding.signInLayout)
        Log.d(TAG, viewModel.userAuthLiveData.value.toString())
        viewModel.userAuthValidate()
    }

    private fun showSnackBar() {
        val snackbar =
            Snackbar.make(binding.signInLayout, "Invalid User", Snackbar.LENGTH_LONG)
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

    fun onStartWelcomeActivity(customer: Customer?) {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        intent.putExtra(INTENT_AUTH_CUSTOMER_KEY, customer)
        startActivity(intent)
        finish()
    }
}