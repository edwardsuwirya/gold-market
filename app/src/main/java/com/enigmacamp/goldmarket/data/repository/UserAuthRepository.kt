package com.enigmacamp.goldmarket.data.repository

import com.enigmacamp.goldmarket.data.model.Customer

class UserAuthRepository private constructor() {
    private var dataStore: UserAuthDataStore? = null

    fun init(dataStore: UserAuthDataStore) {
        this.dataStore = dataStore
    }

    fun getUserAuth(email: String, password: String): Customer? {
        return dataStore?.getUserAuth(email, password)
    }

    companion object {
        val instance by lazy { UserAuthRepository() }
    }
}