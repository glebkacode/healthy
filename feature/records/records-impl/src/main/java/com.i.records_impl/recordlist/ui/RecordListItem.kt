package com.i.records_impl.recordlist.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.i.records_impl.core.Record
import com.i.records_impl.recordlist.component.RecordUiModel

@Composable
fun RecordListItem(
    modifier: Modifier = Modifier,
    record: RecordUiModel
) {
    Surface(modifier = modifier) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = record.feelings,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .weight(1f)
                    .padding(bottom = 4.dp)
            )
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = record.pressure,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier
                        .weight(1f)
                        .wrapContentWidth(Alignment.Start)
                )
                Text(
                    text = record.date,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

@Preview
@Composable
fun RecordListItemPreview() {
    MaterialTheme {
        RecordListItem(
            record = RecordUiModel(
                date = "26.06.2023",
                pressure = "120/80",
                feelings = "Все отлично, Все отлично, Все отлично, Все отлично, Все отлично, Все отлично"
            ),
            modifier = Modifier
                .padding(end = 8.dp)
                .size(288.dp, 80.dp),
        )
    }
}
