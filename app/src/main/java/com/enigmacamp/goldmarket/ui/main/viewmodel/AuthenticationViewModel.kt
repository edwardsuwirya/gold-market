package com.enigmacamp.goldmarket.ui.main.viewmodel

import androidx.lifecycle.ViewModel

class AuthenticationViewModel : ViewModel() {
    var status = mapOf<Int, String>()

    fun clearStatus() {
        status = mapOf()
    }
}