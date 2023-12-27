package com.example.qaraqalpaqshaszlik.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.qaraqalpaqshaszlik.data.models.ResultData
import com.example.qaraqalpaqshaszlik.data.models.TermData
import com.example.qaraqalpaqshaszlik.data.models.UserData
import com.example.qaraqalpaqshaszlik.domain.usecase.addterm.AddTermDataUseCase
import com.example.qaraqalpaqshaszlik.domain.usecase.getterms.GetAllDataUseCase
import com.example.qaraqalpaqshaszlik.domain.usecase.getuserdata.GetUserDataUseCase
import com.example.qaraqalpaqshaszlik.domain.usecase.rate.RateUseCase

class MainViewModelImpl(
    private val getUserDataUseCase: GetUserDataUseCase,
    private val addTermDataUseCase: AddTermDataUseCase,
    private val getAllDataUseCase: GetAllDataUseCase,
    private val rateUseCase: RateUseCase
) : MainViewModel() {
    override val messageLiveData: LiveData<String>
        get() = _messageLiveData
    private val _messageLiveData = MutableLiveData<String>()
    override val errorLiveData: LiveData<Throwable>
        get() = _errorLiveData
    private val _errorLiveData = MutableLiveData<Throwable>()

    override val userDataLiveData: LiveData<List<UserData>>
        get() = _userDataLiveData
    private val _userDataLiveData = MutableLiveData<List<UserData>>()
    override suspend fun getUserData() {
        getUserDataUseCase.execute().collect {
            when (it) {
                is ResultData.Success -> _userDataLiveData.value = it.success!!
                is ResultData.Message -> _messageLiveData.value = it.message
                is ResultData.Error -> _errorLiveData.value = it.error
            }
        }
    }

    override val addTermLiveData: LiveData<String>
        get() = _addTermLiveData
    private val _addTermLiveData = MutableLiveData<String>()
    override suspend fun addTermData(termData: TermData) {
        addTermDataUseCase.execute(termData)
    }

    override val allDataLiveData: LiveData<List<TermData>>
        get() = _allDataLiveData
    private val _allDataLiveData = MutableLiveData<List<TermData>>()
    override suspend fun getAllData() {
        getAllDataUseCase.execute().collect {
            when (it) {
                is ResultData.Success -> _allDataLiveData.value = it.success!!
                is ResultData.Message -> _messageLiveData.value = it.message
                is ResultData.Error -> _errorLiveData.value = it.error
            }
        }
    }

    override suspend fun rate(like: Boolean, termId: String, userPath: String) {
        rateUseCase.execute(like, termId, userPath)
    }
}