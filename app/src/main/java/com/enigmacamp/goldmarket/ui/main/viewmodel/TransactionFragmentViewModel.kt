package com.enigmacamp.goldmarket.ui.main.viewmodel

import android.text.TextWatcher
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enigmacamp.goldmarket.data.model.AppState
import com.enigmacamp.goldmarket.data.model.Customer
import com.enigmacamp.goldmarket.data.repository.CustomerBalanceRepository
import com.enigmacamp.goldmarket.ui.main.view.fragments.HomeFragment
import com.enigmacamp.goldmarket.util.AppTextWatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class TransactionFragmentViewModel(private val customerBalanceRepository: CustomerBalanceRepository) :
    ViewModel() {
    val TAG = TransactionFragmentViewModel::class.qualifiedName
    var pricePerGr = 0.0f
    var customer: Customer? = null

    var transactionGoldRupiah = MutableLiveData("0")
    var transactionGoldGram = MutableLiveData(0.0f)

    var goldAmountDeal = MutableLiveData(Pair(0.0f, 0.0f))

    var transactionType: String = ""

    private val _responseTransaction = MutableLiveData<AppState<Nothing>>()
    val responseTransaction: LiveData<AppState<Nothing>>
        get() = _responseTransaction

    fun getAmountRpTextWatcher(): TextWatcher {
        return AppTextWatcher {
            afterChanged = {
                var rupiah = if (it.isNullOrBlank()) "0.0" else it.toString()
                requestTransaction(rupiah)
            }
        }
    }

    fun calculateGold(forAmount: Float): Float {

        return forAmount / pricePerGr
    }

    fun setPrice(goldPrice: Float) {
        Log.d(TAG, goldPrice.toString())
        pricePerGr = goldPrice
    }

    fun requestTransaction(amount: String) {
        val goldAmount = amount.toFloat()
        val goldAmountGr = calculateGold(goldAmount)
        transactionGoldGram.value = goldAmountGr
        goldAmountDeal.value = Pair(goldAmount, goldAmountGr)
    }

    fun submitTransaction() {
        viewModelScope.launch {
            _responseTransaction.value = AppState.Loading()
            delay(1000)
            val goldGramTrx =
                if (transactionType == HomeFragment.TRX_BUY) goldAmountDeal.value?.second
                else goldAmountDeal.value?.second ?: 0 * -1
            customerBalanceRepository.updateCustomerBalance(
                customer?.customerId ?: "",
                goldAmountDeal.value?.second ?: 0.0f
            )
            _responseTransaction.value = AppState.Success()
        }

    }
}