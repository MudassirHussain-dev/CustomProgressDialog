package com.hmdrh.crudmvvm.models
data class LoginResponse(
    val error: String,
    val message: String,
    val user: User
)