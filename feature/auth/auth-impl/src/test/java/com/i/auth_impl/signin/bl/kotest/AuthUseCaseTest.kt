package com.i.auth_impl.signin.bl.kotest

import com.i.auth_impl.core.AuthRepository
import com.i.auth_impl.core.User
import com.i.auth_impl.signin.bl.AuthUseCase
import com.i.auth_impl.signin.bl.AuthUseCaseImpl
import io.kotest.core.spec.style.StringSpec
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk

class AuthUseCaseTest : StringSpec({

    val repository = mockk<AuthRepository>()
    val useCase: AuthUseCase = AuthUseCaseImpl(repository)

    "auth use case called successful" {
        val inputUser = User("email", "password")
        coEvery { repository.authAccount(inputUser) } returns Unit

        useCase.invoke(inputUser)

        coVerify(exactly = 1) { repository.authAccount(inputUser) }
    }
})