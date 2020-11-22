package com.enigmacamp.goldmarket.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enigmacamp.goldmarket.data.model.AppState
import com.enigmacamp.goldmarket.data.model.Customer
import com.enigmacamp.goldmarket.data.model.CustomerBalance
import com.enigmacamp.goldmarket.data.model.GoldPrice
import com.enigmacamp.goldmarket.data.repository.CustomerBalanceRepository
import com.enigmacamp.goldmarket.data.repository.GoldPriceRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.DecimalFormat
import java.util.*

class HomeFragmentViewModel(
    private val goldPriceRepo: GoldPriceRepository,
    private val customerBalanceRepository: CustomerBalanceRepository
) : ViewModel() {
    val goldPrice = MutableLiveData<GoldPrice>()
    val customerBalance = MutableLiveData<CustomerBalance>()
    val customerGoldInRp = MutableLiveData<Double>()

    private val _response = MutableLiveData<AppState<Nothing>>()
    val response: LiveData<AppState<Nothing>>
        get() = _response

    fun getCurrentGoldPriceAndCalculateCustomerBalance(customer: Customer?) {
        viewModelScope.launch {
            _response.value = AppState.Loading()
            delay(1000)
            val price = goldPriceRepo.getGoldPrice(Date())
            if (price != null && !customer?.customerId.isNullOrEmpty()) {
                var balance =
                    customerBalanceRepository.getCustomerBalance(customer?.customerId ?: "")
                customerBalance.value = balance
                customerGoldInRp.value = (balance?.goldInGram ?: 0.0) * price.buyingPrice

                goldPrice.value = price
                _response.value = AppState.Success()
            } else {
                _response.value = AppState.Error(Exception("Something goes wrong!!!"))
            }
        }
    }


}