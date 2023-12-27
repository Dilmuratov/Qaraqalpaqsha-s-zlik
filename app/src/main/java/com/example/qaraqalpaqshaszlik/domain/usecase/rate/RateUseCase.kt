package com.example.qaraqalpaqshaszlik.domain.usecase.rate

interface RateUseCase {
    suspend fun execute(like: Boolean, termId: String, userPath: String)
}