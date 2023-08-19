package com.i.auth_impl.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.i.auth_impl.core.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SignInViewModel(
    private val authUseCase: AuthUseCase
) : ViewModel() {

    fun onSignInActionClicked(
        email: String,
        password: String,
        onSignInSuccess: () -> Unit
    ) {
        viewModelScope.launch(Dispatchers.Default) {
            val user = User(email, password)
            authUseCase(user)
            withContext(Dispatchers.Main) {
                onSignInSuccess()
            }
        }
    }
}
