package com.example.qaraqalpaqshaszlik.domain.usecase.getuserdata

import com.example.qaraqalpaqshaszlik.data.models.ResultData
import com.example.qaraqalpaqshaszlik.data.models.UserData
import kotlinx.coroutines.flow.Flow

interface GetUserDataUseCase {
    suspend fun execute(): Flow<ResultData<List<UserData>>>
}