package com.example.qaraqalpaqshaszlik.di

import com.example.qaraqalpaqshaszlik.data.repository.MainRepositoryImpl
import com.example.qaraqalpaqshaszlik.domain.MainRepository
import com.google.firebase.Firebase
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import org.koin.dsl.module

val dataModule = module {
    single<MainRepository> {
        MainRepositoryImpl(
            fireStore = FirebaseFirestore.getInstance(),
            realTimeDatabase = FirebaseDatabase.getInstance("https://qaraqalpaqsha-so-zlik-default-rtdb.asia-southeast1.firebasedatabase.app/")
        )
    }
}