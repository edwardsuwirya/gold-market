package com.enigmacamp.goldmarket.data.repository

import com.enigmacamp.goldmarket.data.model.Customer

class UserAuthDataStoreImpl : UserAuthDataStore {
    override fun getUserAuth(email: String, password: String): Customer? {
        if (email == "enigma" && password == "123") {
            return Customer("123", "Enigma", "Camp", "it@enigmacamp.com")
        } else {
            return null
        }
    }
}