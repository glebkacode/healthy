package com.i.auth_impl.signin.component

import com.i.auth_impl.signin.store.SignInStore

internal fun stateToUi(state: SignInStore.State) = SignInUiModel(
    email = state.email,
    password = state.password,
    isEmailInvalid = state.invalidEmail,
    isPasswordInvalid = state.invalidPassword
)
