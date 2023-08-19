package com.i.auth_impl.signin

import com.i.auth_impl.core.AuthRepository
import com.i.auth_impl.core.User

class AuthUseCaseImpl(
    private val authRepository: AuthRepository
) : AuthUseCase {

    override suspend fun invoke(user: User) {
        authRepository.authAccount(user)
    }
}
