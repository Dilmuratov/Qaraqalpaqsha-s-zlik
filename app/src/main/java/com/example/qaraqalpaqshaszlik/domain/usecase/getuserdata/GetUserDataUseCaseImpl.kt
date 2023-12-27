package com.example.qaraqalpaqshaszlik.domain.usecase.getuserdata

import com.example.qaraqalpaqshaszlik.domain.MainRepository

class GetUserDataUseCaseImpl(private val mainRepository: MainRepository) : GetUserDataUseCase {
    override suspend fun execute() =
        mainRepository.getUserData()
}