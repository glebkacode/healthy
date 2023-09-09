package com.i.records_impl.recordlist.component

import com.i.records_impl.recordlist.store.RecordsListStore

internal fun stateToUi(state: RecordsListStore.State): RecordsUiModel {
    return RecordsUiModel(
        records = state.records.map { record ->
            RecordUiModel(
                date = record.date,
                pressure = record.pressure,
                feelings = record.feelings
            )
        }
    )
}