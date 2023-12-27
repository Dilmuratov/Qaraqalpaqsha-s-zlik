package com.example.qaraqalpaqshaszlik.domain.usecase.addterm

import com.example.qaraqalpaqshaszlik.data.models.ResultData
import com.example.qaraqalpaqshaszlik.data.models.TermData
import kotlinx.coroutines.flow.Flow

interface AddTermDataUseCase {
    suspend fun execute(termData: TermData)
}