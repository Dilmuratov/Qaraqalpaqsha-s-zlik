package com.example.qaraqalpaqshaszlik.domain.usecase.getterms

import com.example.qaraqalpaqshaszlik.data.models.ResultData
import com.example.qaraqalpaqshaszlik.domain.MainRepository
import kotlinx.coroutines.flow.Flow

class GetAllDataUseCaseImpl(private val mainRepository: MainRepository): GetAllDataUseCase {
    override suspend fun execute() = mainRepository.getAllData()
}