package com.i.auth_impl.core

interface AuthRepository {
    suspend fun authAccount(user: User)
    suspend fun createAccount(user: User)
}
