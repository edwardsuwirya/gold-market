package com.enigmacamp.goldmarket.ui.main.view.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.enigmacamp.goldmarket.R
import com.enigmacamp.goldmarket.databinding.ActivitySignUpBinding
import com.enigmacamp.goldmarket.ui.LoadingDialog
import com.enigmacamp.goldmarket.ui.main.viewmodel.SignUpViewModel


class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    lateinit var loadingDialog: AlertDialog
    lateinit var viewModel: SignUpViewModel

    private val TAG = SignUpActivity::class.qualifiedName

    companion object {
        private const val ENIGMA_URL = "https://www.enigmacamp.com"
    }

    private fun initUi() {
        loadingDialog = LoadingDialog.build(this)
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(SignUpViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)
        initUi()
        initViewModel()
        binding.apply {
            customer = viewModel.customer
            signinButton.setOnClickListener {
                onGotoSignInActivity()
            }

            newSignupButton.setOnClickListener {
                Log.d(TAG, viewModel.customer.toString())
                onShowLoadingDialog()
                onCloseLoadingDialog()
            }
            tcButton.setOnClickListener {
                onGoToTermConditionActivity()
            }
        }
        print("onCreate")
    }

    private fun onShowLoadingDialog() {
        loadingDialog.show()
    }

    private fun onCloseLoadingDialog() {
        loadingDialog.dismiss()
    }

    private fun onGoToTermConditionActivity() {
        val intent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse(ENIGMA_URL)
        )
        startActivity(intent)
    }

    private fun onGotoSignInActivity() {
        val intent = Intent(this, SignInActivity::class.java)
        //Prevent multiple new activity
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent)
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