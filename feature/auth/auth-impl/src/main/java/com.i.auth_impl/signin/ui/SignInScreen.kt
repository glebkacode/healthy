@file:OptIn(ExperimentalMaterial3Api::class)

package com.i.auth_impl.signin.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
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
import com.i.auth_impl.signin.component.Event
import com.i.auth_impl.signin.component.SignInComponent
import com.i.auth_impl.signin.component.SignInUiModel
import org.koin.androidx.compose.get

@Composable
fun SignInScreen(component: SignInComponent = get()) {
    val uiModel: SignInUiModel by component.ui.collectAsState(SignInUiModel())
    SignInUi(
        uiModel = uiModel,
        onEmailChanged = { text ->
            component.dispatch(Event.EmailTextInput(text))
        },
        onPasswordChanged = { text ->
            component.dispatch(Event.PasswordTextInput(text))
        },
        onApplySignInClicked = {
            component.dispatch(Event.SignInClicked)
        }
    )
}

@Composable
fun SignInUi(
    uiModel: SignInUiModel,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onApplySignInClicked: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentWidth(align = Alignment.CenterHorizontally)
            .widthIn(max = 840.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Branding()
            SignInAccount(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                email = uiModel.email,
                isEmailError = uiModel.isEmailInvalid,
                password = uiModel.password,
                isPasswordError = uiModel.isPasswordInvalid,
                onEmailChanged = onEmailChanged,
                onPasswordChanged = onPasswordChanged,
                onApplySignInClicked = onApplySignInClicked
            )
        }
    }
}

@Composable
fun SignInAccount(
    modifier: Modifier = Modifier,
    email: String,
    isEmailError: Boolean,
    password: String,
    isPasswordError: Boolean,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onApplySignInClicked: () -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = "Sign in or create an account",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 64.dp, bottom = 12.dp)
        )
        Email(email, isEmailError) { text -> onEmailChanged(text) }
        Spacer(modifier = Modifier.padding(top = 8.dp))
        Password(password, isPasswordError) { text -> onPasswordChanged(text) }
        Button(
            onClick = { onApplySignInClicked() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ) {
            Text(
                text = "Sign in",
                style = MaterialTheme.typography.bodyMedium,
            )
        }
        OrSignUpAccount(
            modifier = Modifier.fillMaxWidth(),
            onSignUpRequired = {
                // onSignUpRequired()
            }
        )
    }
}

@Composable
fun OrSignUpAccount(
    modifier: Modifier = Modifier,
    onSignUpRequired: () -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "or",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
            textAlign = TextAlign.Center
        )
        OutlinedButton(
            onClick = { onSignUpRequired() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Text(
                text = "Sign up"
            )
        }
    }
}

@Preview
@Composable
fun AuthScreenPreview() {
    MaterialTheme {
        SignInUi(
            uiModel = SignInUiModel("email", "password"),
            onEmailChanged = {},
            onPasswordChanged = {},
            onApplySignInClicked = {}
        )
    }
}
