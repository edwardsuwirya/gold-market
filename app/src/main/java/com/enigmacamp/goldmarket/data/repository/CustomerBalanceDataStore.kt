package com.enigmacamp.goldmarket.data.repository

import com.enigmacamp.goldmarket.data.model.CustomerBalance

interface CustomerBalanceDataStore {
    fun getBalance(id: String): CustomerBalance?
    fun updateBalance(id: String, goldGram: Float)
}