package com.i.auth_impl.signup

import com.i.auth_impl.core.User

interface CreateAccountUseCase {
    suspend operator fun invoke(user: User)
}
