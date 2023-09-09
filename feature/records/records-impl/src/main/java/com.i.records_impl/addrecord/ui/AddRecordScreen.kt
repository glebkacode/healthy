package com.i.records_impl.addrecord.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.i.records_impl.R
import com.i.records_impl.addrecord.component.AddRecordComponent
import com.i.records_impl.addrecord.component.AddRecordUiModel
import com.i.records_impl.addrecord.component.Event
import org.koin.androidx.compose.get

@Composable
fun AddRecordScreen(
    component: AddRecordComponent = get()
) {
    val uiModel: AddRecordUiModel by component.ui.collectAsState(AddRecordUiModel())
    AddRecordUI(
        date = uiModel.date,
        pressure = uiModel.pressure,
        feelings = uiModel.feelings,
        onDateChanged = { text -> component.dispatch(Event.DateTextChanged(text)) },
        onPressureChanged = { text -> component.dispatch(Event.PressureTextChanged(text)) },
        onFeelingsChanged = { text -> component.dispatch(Event.FeelingsTextChanged(text)) },
        onAddButtonClicked = { component.dispatch(Event.AddRecordClicked) }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddRecordUI(
    date: String,
    pressure: String,
    feelings: String,
    onDateChanged: (String) -> Unit,
    onPressureChanged: (String) -> Unit,
    onFeelingsChanged: (String) -> Unit,
    onAddButtonClicked: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = date,
            onValueChange = { text -> onDateChanged(text) },
            placeholder = {
                val text = stringResource(id = R.string.add_record_date_hint)
                Text(text)
            }
        )

        TextField(
            modifier = Modifier.padding(top = 16.dp),
            value = pressure,
            onValueChange = { text -> onPressureChanged(text) },
            placeholder = {
                val text = stringResource(id = R.string.add_record_pressure_hint)
                Text(text)
            }
        )

        TextField(
            modifier = Modifier.padding(top = 16.dp),
            value = feelings,
            onValueChange = { text -> (onFeelingsChanged(text)) },
            placeholder = {
                val text = stringResource(id = R.string.add_record_feelings_hint)
                Text(text)
            }
        )

        Button(
            modifier = Modifier.padding(top = 16.dp),
            onClick = { onAddButtonClicked() },
        ) {
            // val text = stringResource(id = R.string.add_record_create_action)
            Text("Создать")
        }
    }
}

@Preview
@Composable
fun AddRecordPreview() {
    MaterialTheme {
        AddRecordUI(
            date = "Date",
            pressure = "Pressure",
            feelings = "Feelings",
            onDateChanged = {},
            onPressureChanged = {},
            onFeelingsChanged = {},
            onAddButtonClicked = {}
        )
    }
}
