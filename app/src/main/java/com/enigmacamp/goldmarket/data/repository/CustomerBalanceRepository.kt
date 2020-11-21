package com.enigmacamp.goldmarket.data.repository

import com.enigmacamp.goldmarket.data.model.CustomerBalance

class CustomerBalanceRepository private constructor() {
    private var dataStore: CustomerBalanceDataStore? = null

    fun init(dataStore: CustomerBalanceDataStore) {
        this.dataStore = dataStore
    }

    fun getCustomerBalance(id: String): CustomerBalance? {
        return dataStore?.getBalance(id)
    }

    companion object {
        val instance by lazy { CustomerBalanceRepository() }
    }
}