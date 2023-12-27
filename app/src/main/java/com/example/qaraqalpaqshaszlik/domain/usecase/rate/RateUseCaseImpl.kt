package com.example.qaraqalpaqshaszlik.domain.usecase.rate

import com.example.qaraqalpaqshaszlik.domain.MainRepository

class RateUseCaseImpl(private val mainRepository: MainRepository) : RateUseCase {
    override suspend fun execute(like: Boolean, termId: String, userPath: String) =
        mainRepository.rate(like, termId, userPath)
}