package com.i.auth_impl.signup.component

import com.i.auth_impl.signup.store.SignUpStore

internal fun stateToUi(state: SignUpStore.State) = SignUpUiModel(
    email = state.email,
    password = state.password,
    isEmailInvalid = state.invalidEmail,
    isPasswordInvalid = state.invalidPassword
)