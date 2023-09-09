package com.i.auth_impl.signup.component

sealed interface Event {
    data class EmailTextInput(val email: String) : Event
    data class PasswordTextInput(val password: String) : Event
    object SignUpClicked : Event
}
