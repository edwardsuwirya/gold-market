package com.enigmacamp.goldmarket.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import com.enigmacamp.goldmarket.data.model.Customer
import com.enigmacamp.goldmarket.data.model.CustomerBalance
import com.enigmacamp.goldmarket.data.model.UserAuth

class SignInViewModel : ViewModel() {
    val userAuth = UserAuth()

    fun userAuthValidate(): Pair<Customer?, CustomerBalance?> {
        if (userAuth.userName == "enigma" && userAuth.userPassword == "123") {
            val authCustomer = Customer("123", "Enigma", "Camp", "it@enigmacamp.com")
            val customerBalance = CustomerBalance("123", 100)
            return Pair(authCustomer, customerBalance)
        } else {
            return Pair(null, null)
        }
    }
}