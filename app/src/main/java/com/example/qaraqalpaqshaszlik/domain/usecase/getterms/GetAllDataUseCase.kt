package com.example.qaraqalpaqshaszlik.domain.usecase.getterms

import com.example.qaraqalpaqshaszlik.data.models.ResultData
import com.example.qaraqalpaqshaszlik.data.models.TermData2
import kotlinx.coroutines.flow.Flow

interface GetAllDataUseCase {
    suspend fun execute(): Flow<ResultData<List<TermData2>>>
}