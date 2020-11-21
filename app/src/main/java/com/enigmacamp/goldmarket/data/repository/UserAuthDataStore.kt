package com.enigmacamp.goldmarket.data.repository

import com.enigmacamp.goldmarket.data.model.Customer

interface UserAuthDataStore {
     fun getUserAuth(email: String, password: String): Customer?
}