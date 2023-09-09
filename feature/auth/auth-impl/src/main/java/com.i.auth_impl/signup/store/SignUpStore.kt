package com.i.auth_impl.signup.store

import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.utils.JvmSerializable
import com.i.auth_impl.signup.store.SignUpStore.Intent
import com.i.auth_impl.signup.store.SignUpStore.State

interface SignUpStore : Store<Intent, State, Nothing> {

    sealed class Intent : JvmSerializable {
        data class EmailChanged(val text: String) : Intent()
        data class PasswordChanged(val text: String) : Intent()
        data object SignUpUser : Intent()
    }

    data class State(
        val email: String = "",
        val password: String = "",
        val invalidEmail: Boolean = false,
        val invalidPassword: Boolean = false
    ) : JvmSerializable
}
