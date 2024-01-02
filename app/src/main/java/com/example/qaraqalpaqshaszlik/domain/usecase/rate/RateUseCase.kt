package com.example.qaraqalpaqshaszlik.domain.usecase.rate

import com.example.qaraqalpaqshaszlik.data.models.TermData

interface RateUseCase {
    suspend fun execute(like: Boolean, termId: String, termData: TermData)
}