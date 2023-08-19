package com.i.auth_impl.signup

import com.i.auth_impl.core.AuthRepository
import com.i.auth_impl.core.User

class CreateAccountUseCaseImpl(
    private val authRepository: AuthRepository
) : CreateAccountUseCase {

    override suspend fun invoke(user: User) {
        authRepository.createAccount(user)
    }
}
