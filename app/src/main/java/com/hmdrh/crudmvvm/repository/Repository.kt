package com.hmdrh.crudmvvm.repository

import com.hmdrh.crudmvvm.models.LoginResponse
import com.hmdrh.crudmvvm.models.RegisterResponse
import com.hmdrh.crudmvvm.models.nested_model.GSLogin
import com.hmdrh.crudmvvm.network.api.Client
import retrofit2.Response


class Repository {
    suspend fun RegisterResponse(gsLogin: GSLogin): Response<RegisterResponse> {
        return Client.simpleApi.Register(
            gsLogin.UserName,
            gsLogin.Email,
            gsLogin.Password
        )
    }

    suspend fun Login(Email: String, Password: String): Response<LoginResponse> {
        return Client.simpleApi.Login(Email, Password)
    }
}
