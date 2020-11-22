package com.enigmacamp.goldmarket.ui.main.viewmodel

import androidx.lifecycle.ViewModelProvider
import com.enigmacamp.goldmarket.data.repository.*

object SignInViewModelInjector {
    private fun provideUserAuthRepo(): UserAuthRepository {
        return UserAuthRepository.instance.apply {
            init(UserAuthDataStoreImpl())
        }
    }


    fun getFactory(): ViewModelProvider.Factory {
        return SignInViewModelFactory(provideUserAuthRepo())
    }
}