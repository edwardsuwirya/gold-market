package com.enigmacamp.goldmarket.ui.main.viewmodel

import androidx.lifecycle.ViewModelProvider
import com.enigmacamp.goldmarket.data.repository.CustomerBalanceDataStoreImpl
import com.enigmacamp.goldmarket.data.repository.CustomerBalanceRepository
import com.enigmacamp.goldmarket.data.repository.GoldPriceDataStoreImpl
import com.enigmacamp.goldmarket.data.repository.GoldPriceRepository

object TransactionFragmentViewModelInjector {
    private fun provideCustomerBalanceRepo(): CustomerBalanceRepository {
        return CustomerBalanceRepository.instance.apply {
            init(CustomerBalanceDataStoreImpl())
        }
    }

    fun getFactory(): ViewModelProvider.Factory {
        return TransactionFragmentViewModelFactory(provideCustomerBalanceRepo())
    }
}