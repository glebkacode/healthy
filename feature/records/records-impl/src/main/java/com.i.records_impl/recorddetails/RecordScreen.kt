package com.i.records_impl.recorddetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun RecordScreen() {
    val viewModel: RecordDetailsViewModel = koinViewModel()
    val recordUi = viewModel.recordState.collectAsState()
    RecordUi(record = recordUi.value)
}

@Composable
fun RecordUi(record: RecordUiModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 60.dp)
    ) {
        Text(
            text = record.date,
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        )
        Text(
            text = record.pressure,
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        )
        Text(
            text = record.feelings,
            style = MaterialTheme.typography.bodyMedium
        )
    }
    LaunchedEffect(key1 = Unit, block = {
        launch {}
    })
    SideEffect {}
    DisposableEffect(key1 = Unit, effect = {
        onDispose {}
    })
}

@Preview
@Composable
fun RecordScreenPreview() {
    MaterialTheme {
        RecordUi(
            record = RecordUiModel(
                date = "26.06.2023",
                pressure = "120 / 80",
                feelings = "Excellent"
            )
        )
    }
}
