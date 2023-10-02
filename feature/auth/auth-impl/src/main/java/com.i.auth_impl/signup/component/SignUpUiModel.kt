package com.i.auth_impl.signup.component

data class SignUpUiModel(
    val email: String = "",
    val password: String = "",
    val isEmailInvalid: Boolean = false,
    val isPasswordInvalid: Boolean = false
)
