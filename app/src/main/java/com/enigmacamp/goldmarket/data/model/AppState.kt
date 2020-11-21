package com.enigmacamp.goldmarket.data.model

sealed class AppState<T> {
    class Loading<T>() : AppState<T>()
    class Success<T>(val data: T? = null) : AppState<T>()
    class Error<T>(val throwable: Throwable) : AppState<T>()
}