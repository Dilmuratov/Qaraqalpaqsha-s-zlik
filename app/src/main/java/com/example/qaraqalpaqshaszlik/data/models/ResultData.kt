package com.example.qaraqalpaqshaszlik.data.models

sealed class ResultData<out T> {
    data class Success<T>(val success: T) : ResultData<T>()
    data class Message<T>(val message: String) : ResultData<T>()
    data class Error<T>(val error: Throwable) : ResultData<T>()
}
