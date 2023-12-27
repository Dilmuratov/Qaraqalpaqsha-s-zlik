package com.example.qaraqalpaqshaszlik.data.repository

import android.util.Log
import com.example.qaraqalpaqshaszlik.data.models.ResultData
import com.example.qaraqalpaqshaszlik.data.models.TermData
import com.example.qaraqalpaqshaszlik.data.models.TermData2
import com.example.qaraqalpaqshaszlik.data.models.UserData
import com.example.qaraqalpaqshaszlik.domain.MainRepository
import com.google.firebase.Firebase
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.database
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
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
                        userId = it["userId"].toString(),
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
        realTimeDatabase.getReference("terms").child(termData.termId.toString()).setValue(termData)
            .addOnSuccessListener {
                Log.d("TTTT", "addTerm: Success")
            }
            .addOnFailureListener {
                Log.d("TTTT", "addTerm: Failure")
            }
    }

    override suspend fun getAllData(): Flow<ResultData<List<TermData2>>> = flow {
        emit(
            ResultData.Success(
                realTimeDatabase.getReference("terms").get().await().children.mapNotNull { s1 ->
                    TermData2(
                        termId = s1.child("termId").value.toString(),
                        term = s1.child("term").value.toString(),
                        ownerPath = s1.child("ownerPath").value.toString(),
                        ownerId = s1.child("ownerId").value.toString(),
                        like = s1.child("like").children.mapNotNull { s2 ->
                            s2.key.toString()
                        },
                        dislike = s1.child("dislike").children.mapNotNull { s3 ->
                            s3.key.toString()
                        }
                    )
                }
            )
        )
    }
}
