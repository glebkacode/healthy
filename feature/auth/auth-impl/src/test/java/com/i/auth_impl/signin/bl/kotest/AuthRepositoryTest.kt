package com.i.auth_impl.signin.bl.kotest

import com.i.auth_impl.core.AuthRepository
import com.i.auth_impl.core.AuthRepositoryImpl
import com.i.auth_impl.core.AuthService
import com.i.auth_impl.core.CreateUserRequest
import com.i.auth_impl.core.LoginUserRequest
import com.i.auth_impl.core.User
import io.kotest.core.spec.style.StringSpec
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk

class AuthRepositoryTest : StringSpec({
    val service = mockk<AuthService>()
    val repository: AuthRepository = AuthRepositoryImpl(service)

    "auth user is successful" {
        val user = User(
            email = "email",
            password = "password"
        )
        val request = mockk<LoginUserRequest> {
            every { email } returns "email"
            every { password } returns "password"
        }
        coEvery { service.loginUser(request) } returns Unit

        repository.authAccount(user)

        coVerify { service.loginUser(request) }
    }

    "create user is successful" {
        val user = User(
            email = "email",
            password = "password"
        )
        val request = mockk<CreateUserRequest> {
            every { email } returns "email"
            every { password } returns "password"
        }
        coEvery { service.createUser(request) } returns Unit

        repository.createAccount(user)

        coVerify { service.createUser(request) }
    }
})