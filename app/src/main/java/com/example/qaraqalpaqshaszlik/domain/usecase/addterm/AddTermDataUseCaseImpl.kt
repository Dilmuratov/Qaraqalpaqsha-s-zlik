package com.example.qaraqalpaqshaszlik.domain.usecase.addterm

import com.example.qaraqalpaqshaszlik.data.models.TermData
import com.example.qaraqalpaqshaszlik.domain.MainRepository

class AddTermDataUseCaseImpl(private val mainRepository: MainRepository) : AddTermDataUseCase {
    override suspend fun execute(termData: TermData) = mainRepository.addTerm(termData)
}