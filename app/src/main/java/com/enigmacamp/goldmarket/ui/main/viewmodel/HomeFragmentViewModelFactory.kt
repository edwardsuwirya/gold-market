package com.enigmacamp.goldmarket.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.enigmacamp.goldmarket.data.repository.CustomerBalanceRepository
import com.enigmacamp.goldmarket.data.repository.GoldPriceRepository

class HomeFragmentViewModelFactory(
    private val goldPriceRepository: GoldPriceRepository,
    private val customerBalanceRepo: CustomerBalanceRepository
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeFragmentViewModel::class.java)) {
            return HomeFragmentViewModel(goldPriceRepository, customerBalanceRepo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}