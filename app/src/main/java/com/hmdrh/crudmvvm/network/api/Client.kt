package com.hmdrh.crudmvvm.network.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Client {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("http://192.168.2.39/UserApi/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val simpleApi: SimpleApi by lazy {
        retrofit.create(SimpleApi::class.java)
    }
}