package com.i.auth_impl.signin.bl.mockk

import app.cash.turbine.test
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.tableOf
import com.i.auth_impl.core.AuthRepository
import com.i.auth_impl.core.AuthRepositoryImpl
import com.i.auth_impl.core.AuthService
import com.i.auth_impl.core.CreateUserRequest
import com.i.auth_impl.core.LoginUserRequest
import com.i.auth_impl.core.User
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class AuthRepositoryTest {
    private val service = mockk<AuthService>(relaxed = true)
    private val repository: AuthRepository = AuthRepositoryImpl(service)

    @Test
    fun `auth user is successful`() = runTest {
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

        coVerify(exactly = 1) { service.loginUser(request) }
    }

    @Test
    fun `create user is successful`() = runTest {
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

        coVerify(exactly = 1) { service.createUser(request) }
    }

    @Test
    fun `create user was failed`() = runTest {
        val expected = NullPointerException()
        val user = User(
            email = "email",
            password = "password"
        )
        val request = mockk<CreateUserRequest> {
            every { email } returns "email"
            every { password } returns "password"
        }
        coEvery { service.createUser(request) } throws expected

        val actual = assertThrows<NullPointerException> {
            repository.createAccount(user)
        }
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `create table test`() {
        tableOf("first", "second", "expected")
            .row(1, 1, 2)
            .row(1, 2, 3)
            .row(1, 3, 4)
            .row(1, 4, 5)
            .forAll { first, second, expected ->
                val actual = first + second

                assertThat(actual).isEqualTo(expected)
            }
    }

    @Test
    fun `create flow test`() = runTest {
        flowOf("one", "two").test {
            assertEquals("one", awaitItem())
            assertEquals("two", awaitItem())
            awaitComplete()
        }
    }
}