package com.i.records_impl.recordlist.ui

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.i.records_impl.recordlist.component.Event
import com.i.records_impl.recordlist.component.RecordUiModel
import com.i.records_impl.recordlist.component.RecordsListComponent
import com.i.records_impl.recordlist.component.RecordsUiModel
import org.koin.androidx.compose.get

@Composable
fun RecordsListScreen(
    component: RecordsListComponent = get()
) {
    val uiModel: RecordsUiModel by component.ui.collectAsState(RecordsUiModel())
    RecordsListUi(
        records = uiModel.records,
        onAddRecordClicked = { component.dispatch(Event.AddNewRecordClicked) }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecordsListUi(
    records: List<RecordUiModel>,
    onAddRecordClicked: () -> Unit
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onAddRecordClicked() }
            ) {
                LazyColumn {
                    item {
                        Text(
                            text = "Your tracked information",
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentWidth(Alignment.CenterHorizontally)
                        )
                    }
                    items(records) { record ->
                        RecordListItem(
                            record = record,
                            modifier = Modifier
                                .height(96.dp)
                        )
                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 16.dp)
                        )
                    }
                }
            }
        }
    ) { contentPadding ->
        contentPadding
    }
}

@Preview
@Composable
fun RecordsListScreenPreview() {
    MaterialTheme {
        RecordsListUi(emptyList()) {}
    }
}
