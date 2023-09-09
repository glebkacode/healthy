package com.i.auth_impl.signup.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.i.auth_impl.core.Branding
import com.i.auth_impl.core.Email
import com.i.auth_impl.core.Password
import com.i.auth_impl.signup.component.Event
import com.i.auth_impl.signup.component.SignUpComponent
import com.i.auth_impl.signup.component.SignUpUiModel
import org.koin.androidx.compose.get

@Composable
fun SignUpScreen(
    component: SignUpComponent = get()
) {
    val uiModel by component.ui.collectAsState(SignUpUiModel())
    SignUpUi(
        uiModel = uiModel,
        onEmailChanged = { text ->
            component.dispatch(Event.EmailTextInput(text))
        },
        onPasswordChanged = { text ->
            component.dispatch(Event.PasswordTextInput(text))
        }
    )
}

@Composable
fun SignUpUi(
    uiModel: SignUpUiModel,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentWidth(align = Alignment.CenterHorizontally)
            .widthIn(max = 840.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Branding()
            SignUpAccount(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                email = uiModel.email,
                password = uiModel.password,
                isInvalidEmail = uiModel.isEmailInvalid,
                isInvalidPassword = uiModel.isPasswordInvalid,
                onEmailChanged = onEmailChanged,
                onPasswordChanged = onPasswordChanged
            )
        }
    }
}

@Composable
fun SignUpAccount(
    email: String,
    password: String,
    isInvalidEmail: Boolean = false,
    isInvalidPassword: Boolean = false,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = "Create your account",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 64.dp, bottom = 12.dp),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
            textAlign = TextAlign.Center,
        )
        Email(email) { text -> onEmailChanged(text) }
        Spacer(modifier = Modifier.padding(top = 8.dp))
        Password(password) { text -> onPasswordChanged(text) }
        Button(
            onClick = { /*onSignUpCompleted(email, password)*/ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ) {
            Text(
                text = "Sign Up",
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}

@Preview
@Composable
fun SignUpScreenPreview() {
    MaterialTheme {
        SignUpUi(
            uiModel = SignUpUiModel(),
            onEmailChanged = {},
            onPasswordChanged = {}
        )
    }
}
