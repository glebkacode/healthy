package com.i.records_impl.recorddetails.ui

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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.i.records_impl.recorddetails.component.RecordUiModel
import com.i.records_impl.recorddetails.component.RecordsDetailComponent
import kotlinx.coroutines.launch
import org.koin.androidx.compose.get

@Composable
fun RecordScreen(
    component: RecordsDetailComponent = get()
) {
    val uiModel: RecordUiModel by component.ui.collectAsState(RecordUiModel())
    RecordUi(record = uiModel)
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
