package com.i.auth_impl.signin.component

data class SignInUiModel(
    val email: String = "",
    val password: String = "",
    val isEmailInvalid: Boolean = false,
    val isPasswordInvalid: Boolean = false
)
