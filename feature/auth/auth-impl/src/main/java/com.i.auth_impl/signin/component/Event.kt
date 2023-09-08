package com.i.auth_impl.signin.component

sealed interface Event {
    data class EmailTextInput(val email: String) : Event
    data class PasswordTextInput(val password: String) : Event
    object SignInClicked : Event
}