package com.i.auth_impl.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.i.auth_impl.core.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SignUpViewModel(
    private val createAccountUseCase: CreateAccountUseCase
) : ViewModel() {

    fun onSignUpClicked(
        email: String,
        password: String,
        onSignUpSuccess: () -> Unit
    ) {
        viewModelScope.launch(Dispatchers.Default) {
            val user = User(email, password)
            createAccountUseCase(user)
            withContext(Dispatchers.Main) {
                onSignUpSuccess()
            }
        }
    }
}
