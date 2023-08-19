package com.i.auth_impl.signin

import com.i.auth_impl.core.User

interface AuthUseCase {
    suspend operator fun invoke(user: User)
}
