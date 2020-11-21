package com.enigmacamp.goldmarket.ui.main.viewmodel

import androidx.lifecycle.ViewModel

class TransactionFragmentViewModel : ViewModel() {
    var goldAmount = 0.0
    var pricePerGr = 900000
    var sGoldAmount = "0.0"

    fun calculateGold() {
        sGoldAmount = "${"%.4f".format(sGoldAmount.toDouble() / pricePerGr)}"
    }
}