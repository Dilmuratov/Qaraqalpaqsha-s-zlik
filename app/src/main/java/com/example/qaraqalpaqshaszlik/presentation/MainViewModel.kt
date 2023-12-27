package com.example.qaraqalpaqshaszlik.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.qaraqalpaqshaszlik.data.models.TermData
import com.example.qaraqalpaqshaszlik.data.models.TermData2
import com.example.qaraqalpaqshaszlik.data.models.UserData

abstract class MainViewModel : ViewModel() {

    abstract val messageLiveData: LiveData<String>
    abstract val errorLiveData: LiveData<Throwable>

    abstract val userDataLiveData: LiveData<List<UserData>>
    abstract suspend fun getUserData()

    abstract val addTermLiveData: LiveData<String>
    abstract suspend fun addTermData(termData: TermData)

    abstract val allDataLiveData: LiveData<List<TermData2>>
    abstract suspend fun getAllData()
}