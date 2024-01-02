package com.example.qaraqalpaqshaszlik.data.repository

import android.util.Log
import com.example.qaraqalpaqshaszlik.data.models.ResultData
import com.example.qaraqalpaqshaszlik.data.models.TermData
import com.example.qaraqalpaqshaszlik.data.models.UserData
import com.example.qaraqalpaqshaszlik.domain.MainRepository
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class MainRepositoryImpl(
    private val fireStore: FirebaseFirestore,
    private val realTimeDatabase: FirebaseDatabase
) : MainRepository {
    override suspend fun getUserData(): Flow<ResultData<List<UserData>>> = flow {
        emit(
            ResultData.Success(
                fireStore.collection("users").get().await().documents.mapNotNull {
                    UserData(
                        login = it["login"].toString(),
                        password = it["password"].toString(),
                        username = it["username"].toString(),
                        userPath = it.id,
                        monets = it["monets"].toString()
                    )
                })
        )
    }.catch {
        it.printStackTrace()
    }

    override suspend fun addTerm(termData: TermData) {
        realTimeDatabase.getReference("terms").child(termData.termId).setValue(termData)
            .addOnSuccessListener {
                Log.d("TTTT", "addTerm: Success")
            }
            .addOnFailureListener {
                Log.d("TTTT", "addTerm: Failure")
            }
    }

    override suspend fun getAllData(): Flow<ResultData<List<TermData>>> = flow {
        emit(
            ResultData.Success(
                realTimeDatabase.getReference("terms").get().await().children.mapNotNull { s1 ->
                    TermData(
                        termId = s1.child("termId").value.toString(),
                        term = s1.child("term").value.toString(),
                        ownerPath = s1.child("ownerPath").value.toString(),
                        like = s1.child("like").value.toString(),
                        dislike = s1.child("dislike").value.toString()
                    )
                }
            )
        )
    }

    override suspend fun rate(like: Boolean, termId: String, termData: TermData) {
        realTimeDatabase.getReference("terms").child(termId).setValue(termData)
            .addOnSuccessListener {
                Log.d("TTTT", "rate: Success")
            }
            .addOnFailureListener {
                Log.d("TTTT", "rate: Failure")
            }
    }
}
