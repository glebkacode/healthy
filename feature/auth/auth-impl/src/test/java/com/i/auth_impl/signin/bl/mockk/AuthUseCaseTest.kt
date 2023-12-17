package com.i.auth_impl.signin.bl.mockk

import com.i.auth_impl.core.AuthRepository
import com.i.auth_impl.core.User
import com.i.auth_impl.signin.bl.AuthUseCase
import com.i.auth_impl.signin.bl.AuthUseCaseImpl
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test

class AuthUseCaseTest {
    private val repository = mockk<AuthRepository>()
    private val useCase: AuthUseCase = AuthUseCaseImpl(repository)

    @Test
    fun `check auth usecase was successful called`() = runTest {
        val inputUser = User("email", "password")
        coEvery { repository.authAccount(inputUser) } returns Unit

        useCase.invoke(inputUser)

        coVerify { repository.authAccount(inputUser) }
    }
}