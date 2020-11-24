package com.enigmacamp.goldmarket.ui.main.viewmodel

import android.text.TextWatcher
import androidx.lifecycle.ViewModel
import com.enigmacamp.goldmarket.data.model.Customer
import com.enigmacamp.goldmarket.util.AppTextWatcher
import com.enigmacamp.goldmarket.util.DynamicTextWatcher

class SignUpViewModel : ViewModel() {
    var customer = Customer()

    fun getFirstNameTextWatcher(): TextWatcher {
        return AppTextWatcher {
            afterChanged = {
                customer.firstName = it.toString()
            }
        }
    }

    fun getLastNameTextWatcher(): TextWatcher {
        return AppTextWatcher {
            afterChanged = {
                customer.lastName = it.toString()
            }
        }
    }

    fun getEmailTextWatcher(): TextWatcher {
        return AppTextWatcher {
            afterChanged = {
                customer.email = it.toString()
            }
        }
    }

    fun getPasswordTextWatcher(): TextWatcher {
        return AppTextWatcher {
            afterChanged = {
                customer.password = it.toString()
            }
        }
    }
}