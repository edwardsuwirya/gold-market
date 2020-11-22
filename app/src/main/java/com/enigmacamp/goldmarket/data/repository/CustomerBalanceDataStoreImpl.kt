package com.enigmacamp.goldmarket.data.repository

import com.enigmacamp.goldmarket.data.model.CustomerBalance

class CustomerBalanceDataStoreImpl : CustomerBalanceDataStore {
    companion object {
        var customerBalance = CustomerBalance("123", 12.1234)
    }

    override fun getBalance(id: String): CustomerBalance? {
        if (id == "123") {
            return customerBalance
        } else {
            return null
        }
    }

    override fun updateBalance(id: String, goldGram: Float) {
        customerBalance.goldInGram = customerBalance.goldInGram + goldGram
    }
}