package com.i.auth_impl.signin.store

import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.utils.JvmSerializable
import com.i.auth_impl.signin.store.SignInStore.Intent
import com.i.auth_impl.signin.store.SignInStore.State

internal interface SignInStore : Store<Intent, State, Nothing> {

    sealed class Intent : JvmSerializable {
        data class ChangeEmail(val text: String) : Intent()
        data class ChangePassword(val text: String) : Intent()
        object SignIn : Intent()
    }

    data class State(
        val email: String = "",
        val password: String = "",
        val invalidEmail: Boolean = false,
        val invalidPassword: Boolean = false
    ) : JvmSerializable
}
