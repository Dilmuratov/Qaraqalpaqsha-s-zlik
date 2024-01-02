package com.example.qaraqalpaqshaszlik.domain.usecase.rate

import com.example.qaraqalpaqshaszlik.data.models.TermData
import com.example.qaraqalpaqshaszlik.domain.MainRepository

class RateUseCaseImpl(private val mainRepository: MainRepository) : RateUseCase {
    override suspend fun execute(like: Boolean, termId: String, termData: TermData) =
        mainRepository.rate(like, termId, termData)
}