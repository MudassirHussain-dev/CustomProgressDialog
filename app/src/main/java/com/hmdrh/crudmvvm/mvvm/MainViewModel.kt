package com.hmdrh.crudmvvm.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hmdrh.crudmvvm.models.LoginResponse
import com.hmdrh.crudmvvm.models.RegisterResponse
import com.hmdrh.crudmvvm.models.nested_model.GSLogin
import com.hmdrh.crudmvvm.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(val repository: Repository) : ViewModel() {

    val registernResponse = MutableLiveData<Response<RegisterResponse>>()

    val _registernResponse: LiveData<Response<RegisterResponse>>
        get() = registernResponse

    fun RegisterResponse(gsLogin: GSLogin) {
        viewModelScope.launch {
            val response =
                repository.RegisterResponse(gsLogin)
            registernResponse.value = response
        }
    }

    val loginResponse: MutableLiveData<Response<LoginResponse>> = MutableLiveData()

    fun Login(Email: String, Password: String) {
        viewModelScope.launch {
            val response = repository.Login(Email, Password)
            loginResponse.value = response
        }
    }

}