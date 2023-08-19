package com.i.auth_impl.signup

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
fun SignUpScreen(
    onSignUpCompleted: () -> Unit
) {
    val viewModel: SignUpViewModel by koinViewModel()
    SignUpUi { email, password ->
        viewModel.onSignUpClicked(email, password) {
            onSignUpCompleted()
        }
    }
}

@Composable
fun SignUpUi(
    onSignUpCompleted: (String, String) -> Unit
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
                onSignUpCompleted = onSignUpCompleted
            )
        }
    }
}

@Composable
fun SignUpAccount(
    modifier: Modifier = Modifier,
    onSignUpCompleted: (String, String) -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

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
        Email(email) { text ->
            email = text
        }
        Spacer(modifier = Modifier.padding(top = 8.dp))
        Password(password) { text ->
            password = text
        }
        Button(
            onClick = { onSignUpCompleted(email, password) },
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
        SignUpScreen {}
    }
}
