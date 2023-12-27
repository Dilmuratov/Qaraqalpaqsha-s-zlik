package com.example.qaraqalpaqshaszlik.domain

import com.example.qaraqalpaqshaszlik.data.models.ResultData
import com.example.qaraqalpaqshaszlik.data.models.TermData
import com.example.qaraqalpaqshaszlik.data.models.UserData
import kotlinx.coroutines.flow.Flow

interface MainRepository {

    suspend fun getUserData(): Flow<ResultData<List<UserData>>>

    suspend fun addTerm(termData: TermData)

    suspend fun getAllData(): Flow<ResultData<List<TermData>>>

    suspend fun rate(like: Boolean, termId: String, userPath: String)
}