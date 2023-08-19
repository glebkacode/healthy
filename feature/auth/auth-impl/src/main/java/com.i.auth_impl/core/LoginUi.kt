package com.i.auth_impl.core

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.i.auth_impl.R

@Composable
fun Branding(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.wrapContentHeight(align = Alignment.CenterVertically)
    ) {
        Logo(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(horizontal = 76.dp)
        )
        Text(
            text = "Track your pressure with us!",
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(top = 24.dp)
                .fillMaxWidth()
        )
    }
}

@Composable
fun Logo(
    modifier: Modifier = Modifier
) {
    val assetsId = R.drawable.ic_logo_light
    Image(
        painter = painterResource(id = assetsId),
        modifier = modifier,
        contentDescription = null
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Email(
    text: String,
    onTextChanged: (String) -> Unit
) {
    OutlinedTextField(
        value = text,
        onValueChange = { text ->
            onTextChanged(text)
        },
        label = {
            Text(
                text = "Email",
                style = MaterialTheme.typography.bodyMedium
            )
        },
        modifier = Modifier.fillMaxWidth()
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Password(
    text: String,
    onTextChanged: (String) -> Unit
) {
    OutlinedTextField(
        value = text,
        onValueChange = { text ->
            onTextChanged(text)
        },
        label = {
            Text(
                text = "Password",
                style = MaterialTheme.typography.bodyMedium
            )
        },
        modifier = Modifier.fillMaxWidth()
    )
}
