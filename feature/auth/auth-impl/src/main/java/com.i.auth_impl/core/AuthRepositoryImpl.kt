package com.i.auth_impl.core

class AuthRepositoryImpl(
    private val authService: AuthService
) : AuthRepository {

    override suspend fun authAccount(user: User) {
        val request = LoginUserRequest(
            email = user.email,
            password = user.password
        )
        authService.loginUser(request)
    }

    override suspend fun createAccount(user: User) {
        val request = CreateUserRequest(
            email = user.email,
            password = user.password
        )
        authService.createUser(request)
    }
}
