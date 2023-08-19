@file:OptIn(ExperimentalMaterial3Api::class)

package com.i.auth_impl.signin

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.i.auth_impl.core.Branding
import com.i.auth_impl.core.Email
import com.i.auth_impl.core.Password
import org.koin.androidx.compose.koinViewModel

@Composable
fun SignInScreen(
    onSignInCompleted: () -> Unit,
    onSignUpRequired: () -> Unit
) {
    val viewModel: SignInViewModel = koinViewModel()
    SignInUi(
        onSignInCompleted = { email, password ->
            viewModel.onSignInActionClicked(email, password) {
                onSignInCompleted()
            }
        },
        onSignUpRequired = onSignUpRequired
    )
}

@Composable
fun SignInUi(
    onSignInCompleted: (String, String) -> Unit,
    onSignUpRequired: () -> Unit
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
                onSignInCompleted = onSignInCompleted,
                onSignUpRequired = { onSignUpRequired() }
            )
        }
    }
}

@Composable
fun SignInAccount(
    modifier: Modifier = Modifier,
    onSignInCompleted: (String, String) -> Unit,
    onSignUpRequired: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

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
        Email(email) { text ->
            email = text
        }
        Spacer(modifier = Modifier.padding(top = 8.dp))
        Password(password) { text ->
            password = text
        }
        Button(
            onClick = { onSignInCompleted(email, password) },
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
            onSignUpRequired = { onSignUpRequired() }
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
            onSignInCompleted = { _, _ -> },
            onSignUpRequired = {},
        )
    }
}
