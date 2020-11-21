package com.enigmacamp.goldmarket.data.repository

import com.enigmacamp.goldmarket.data.model.CustomerBalance

class CustomerBalanceDataStoreImpl : CustomerBalanceDataStore {
    override fun getBalance(id: String): CustomerBalance? {
        if (id == "123") {
            return CustomerBalance("123", 100)
        } else {
            return null
        }
    }
}