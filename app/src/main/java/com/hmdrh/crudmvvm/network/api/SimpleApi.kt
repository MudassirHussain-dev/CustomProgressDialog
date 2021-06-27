package com.hmdrh.crudmvvm.network.api

import com.hmdrh.crudmvvm.models.LoginResponse
import com.hmdrh.crudmvvm.models.RegisterResponse
import com.hmdrh.crudmvvm.models.nested_model.GSLogin
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface SimpleApi {
    @FormUrlEncoded
    @POST("login.php")
    suspend fun Login(
    @Field("email") email: String,
    @Field("password") password: String,
    ): Response<LoginResponse>

    @FormUrlEncoded
    @POST("register.php")
    suspend fun Register(
        @Field("username") username: String,
        @Field("email") email: String,
        @Field("password") password: String,
    ): Response<RegisterResponse>
}