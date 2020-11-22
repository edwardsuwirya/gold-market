package com.enigmacamp.goldmarket.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.enigmacamp.goldmarket.data.repository.CustomerBalanceRepository
import com.enigmacamp.goldmarket.data.repository.GoldPriceRepository

class TransactionFragmentViewModelFactory(
    private val customerBalanceRepo: CustomerBalanceRepository
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TransactionFragmentViewModel::class.java)) {
            return TransactionFragmentViewModel(customerBalanceRepo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}