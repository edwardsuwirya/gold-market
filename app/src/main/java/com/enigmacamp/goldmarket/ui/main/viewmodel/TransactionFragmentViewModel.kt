package com.enigmacamp.goldmarket.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enigmacamp.goldmarket.data.model.AppState
import com.enigmacamp.goldmarket.data.model.Customer
import com.enigmacamp.goldmarket.data.repository.CustomerBalanceRepository
import com.enigmacamp.goldmarket.ui.main.view.fragments.HomeFragment
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class TransactionFragmentViewModel(private val customerBalanceRepository: CustomerBalanceRepository) :
    ViewModel() {
    var goldAmount = 0.0f
    var pricePerGr = 0.0f
    var customer: Customer? = null
    var goldAmountDeal = MutableLiveData<Pair<Float, String>>()

    var transactionType: String = ""

    private val _responseTransaction = MutableLiveData<AppState<Nothing>>()
    val responseTransaction: LiveData<AppState<Nothing>>
        get() = _responseTransaction

    fun calculateGold(forAmount: Float): Float {
        return forAmount / pricePerGr
    }

    fun setPrice(goldPrice: Float) {
        pricePerGr = goldPrice
    }

    fun requestTransaction(amount: String) {
        goldAmount = amount.toFloat()
        goldAmountDeal.value = Pair(goldAmount, "${"%.4f".format(calculateGold(goldAmount))}")
    }

    fun submitTransaction() {
        viewModelScope.launch {
            _responseTransaction.value = AppState.Loading()
            delay(1000)
            val goldGramTrx =
                if (transactionType == HomeFragment.TRX_BUY) calculateGold(goldAmount)
                else calculateGold(goldAmount) * -1
            customerBalanceRepository.updateCustomerBalance(
                customer?.customerId ?: "",
                goldGramTrx
            )
            _responseTransaction.value = AppState.Success()
        }

    }
}