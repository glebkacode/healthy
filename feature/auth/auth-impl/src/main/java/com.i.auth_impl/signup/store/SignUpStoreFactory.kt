package com.i.auth_impl.signup.store

import android.util.Patterns
import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.SimpleBootstrapper
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.core.utils.JvmSerializable
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.i.auth_impl.signup.store.SignUpStore.Intent
import com.i.auth_impl.signup.store.SignUpStore.State
import kotlin.coroutines.CoroutineContext

private const val MAX_PASSWORD_LENGTH = 10

internal class SignUpStoreFactory(
    private val storeFactory: StoreFactory,
    private val mainCoroutineContext: CoroutineContext,
) {

    fun create(): SignUpStore =
        object : SignUpStore, Store<Intent, State, Nothing> by storeFactory.create(
            name = "SignUpStore",
            initialState = State(),
            bootstrapper = SimpleBootstrapper(Unit),
            executorFactory = ::ExecutorImpl,
            reducer = ReducerImpl
        ) {}

    private inner class ExecutorImpl : CoroutineExecutor<Intent, Unit, State, Msg, Nothing>(mainCoroutineContext) {
        override fun executeAction(action: Unit, getState: () -> State) {
            super.executeAction(action, getState)
        }

        override fun executeIntent(intent: Intent, getState: () -> State) {
            when (intent) {
                is Intent.EmailChanged -> validateEmail(intent.text)
                is Intent.PasswordChanged -> validatePassword(intent.text)
                is Intent.SignUpUser -> signInUser()
            }
        }

        private fun validateEmail(text: String) {
            if (text.isEmpty() or Patterns.EMAIL_ADDRESS.matcher(text).matches().not()) {
                dispatch(Msg.InvalidEmail(text))
            } else {
                dispatch(Msg.EmailChanged(text))
            }
        }

        private fun validatePassword(text: String) {
            if (text.length < MAX_PASSWORD_LENGTH) {
                dispatch(Msg.InvalidPassword(text))
            } else {
                dispatch(Msg.PasswordChanged(text))
            }
        }

        private fun signInUser() {
            dispatch(Msg.SignIn)
        }
    }

    private object ReducerImpl : Reducer<State, Msg> {
        override fun State.reduce(msg: Msg): State {
            return when (msg) {
                is Msg.EmailChanged -> copy(email = msg.text, invalidEmail = false)
                is Msg.PasswordChanged -> copy(password = msg.text, invalidPassword = false)
                is Msg.InvalidEmail -> copy(email = msg.text, invalidEmail = true)
                is Msg.InvalidPassword -> copy(password = msg.text, invalidPassword = true)
                is Msg.SignIn -> copy(
                    email = "",
                    password = "",
                    invalidEmail = false,
                    invalidPassword = false
                )
            }
        }
    }
}

private sealed interface Msg : JvmSerializable {
    @JvmInline
    value class EmailChanged(val text: String) : Msg

    @JvmInline
    value class PasswordChanged(val text: String) : Msg

    @JvmInline
    value class InvalidEmail(val text: String) : Msg

    @JvmInline
    value class InvalidPassword(val text: String) : Msg
    object SignIn : Msg
}
