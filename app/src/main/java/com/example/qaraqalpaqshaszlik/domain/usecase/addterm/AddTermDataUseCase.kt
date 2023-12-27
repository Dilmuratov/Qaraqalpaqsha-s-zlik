package com.example.qaraqalpaqshaszlik.domain.usecase.addterm

import com.example.qaraqalpaqshaszlik.data.models.TermData

interface AddTermDataUseCase {
    suspend fun execute(termData: TermData)
}