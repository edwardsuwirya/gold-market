package com.enigmacamp.goldmarket.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enigmacamp.goldmarket.data.model.AppState
import com.enigmacamp.goldmarket.data.model.Customer
import com.enigmacamp.goldmarket.data.model.UserAuth
import com.enigmacamp.goldmarket.data.repository.UserAuthRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SignInViewModel(
    private val userAuthRepo: UserAuthRepository,
) : ViewModel() {
    private val TAG = SignInViewModel::class.qualifiedName

    var userAuth = UserAuth()
    var userAuthLiveData: MutableLiveData<UserAuth> = MutableLiveData()

    init {
        userAuthLiveData.value = userAuth
    }

    private val _response = MutableLiveData<AppState<Customer?>>()
    val response: LiveData<AppState<Customer?>>
        get() = _response

//    fun userAuthValidate(): Pair<Customer?, CustomerBalance?> {
//        if (userAuth.userName == "enigma" && userAuth.userPassword == "123") {
//            val authCustomer = Customer("123", "Enigma", "Camp", "it@enigmacamp.com")
//            val customerBalance = CustomerBalance("123", 100)
//            return Pair(authCustomer, customerBalance)
//        } else {
//            return Pair(null, null)
//        }
//    }

    fun userAuthValidate() {
        viewModelScope.launch {
            _response.value = AppState.Loading()
            delay(1000)
            val customer = userAuthRepo.getUserAuth(
                userAuthLiveData.value?.userName ?: "",
                userAuthLiveData.value?.userPassword ?: ""
            )
            if (customer != null) {
                _response.value = AppState.Success(customer)
            } else {
                _response.value = AppState.Error(Exception("Unauthorized"))
            }
        }
    }
}