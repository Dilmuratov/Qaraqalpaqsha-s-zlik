package com.example.qaraqalpaqshaszlik.di

import com.example.qaraqalpaqshaszlik.domain.usecase.addterm.AddTermDataUseCase
import com.example.qaraqalpaqshaszlik.domain.usecase.addterm.AddTermDataUseCaseImpl
import com.example.qaraqalpaqshaszlik.domain.usecase.getterms.GetAllDataUseCase
import com.example.qaraqalpaqshaszlik.domain.usecase.getterms.GetAllDataUseCaseImpl
import com.example.qaraqalpaqshaszlik.domain.usecase.getuserdata.GetUserDataUseCase
import com.example.qaraqalpaqshaszlik.domain.usecase.getuserdata.GetUserDataUseCaseImpl
import com.example.qaraqalpaqshaszlik.domain.usecase.rate.RateUseCase
import com.example.qaraqalpaqshaszlik.domain.usecase.rate.RateUseCaseImpl
import org.koin.dsl.module

val domainModule = module {
    single<GetUserDataUseCase> {
        GetUserDataUseCaseImpl(
            mainRepository = get()
        )
    }
    single<AddTermDataUseCase> {
        AddTermDataUseCaseImpl(
            mainRepository = get()
        )
    }
    single<GetAllDataUseCase> {
        GetAllDataUseCaseImpl(
            mainRepository = get()
        )
    }
    single<RateUseCase> {
        RateUseCaseImpl(
            mainRepository = get()
        )
    }
}