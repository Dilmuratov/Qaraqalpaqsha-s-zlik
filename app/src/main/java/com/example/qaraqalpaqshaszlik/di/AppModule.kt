package com.example.qaraqalpaqshaszlik.di

import com.example.qaraqalpaqshaszlik.presentation.MainViewModel
import com.example.qaraqalpaqshaszlik.presentation.MainViewModelImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel <MainViewModel> {
        MainViewModelImpl(
            getUserDataUseCase = get(),
            addTermDataUseCase = get(),
            getAllDataUseCase = get(),
            rateUseCase = get()
        )
    }
}