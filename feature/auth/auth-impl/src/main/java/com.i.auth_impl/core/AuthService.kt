package com.i.auth_impl.core

import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

    @POST("login")
    suspend fun loginUser(@Body request: LoginUserRequest)

    @POST("register")
    suspend fun createUser(@Body request: CreateUserRequest)
}
