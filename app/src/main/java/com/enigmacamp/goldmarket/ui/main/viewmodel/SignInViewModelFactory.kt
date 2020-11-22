package com.enigmacamp.goldmarket.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.enigmacamp.goldmarket.data.repository.CustomerBalanceRepository
import com.enigmacamp.goldmarket.data.repository.UserAuthRepository

class SignInViewModelFactory(
    private val userAuthRepo: UserAuthRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SignInViewModel::class.java)) {
            return SignInViewModel(userAuthRepo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}