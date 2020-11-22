package com.enigmacamp.goldmarket.data.repository

import android.util.Log
import com.enigmacamp.goldmarket.data.model.CustomerBalance

class CustomerBalanceRepository private constructor() {
    private var dataStore: CustomerBalanceDataStore? = null

    fun init(dataStore: CustomerBalanceDataStore) {
        this.dataStore = dataStore
    }

    fun updateCustomerBalance(id: String, goldGram: Float) {
        Log.d("CustomerBalanceRepo", "User : ${id}, gold: ${goldGram}")
        dataStore?.updateBalance(id, goldGram)
    }

    fun getCustomerBalance(id: String): CustomerBalance? {
        return dataStore?.getBalance(id)
    }

    companion object {
        val instance by lazy { CustomerBalanceRepository() }
    }
}