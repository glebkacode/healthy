package com.i.records_impl.recorddetails.component

import com.i.records_impl.recorddetails.store.RecordDetailsStore

internal fun stateToUi(state: RecordDetailsStore.State): RecordUiModel {
    return RecordUiModel(
        date = state.record?.date ?: "",
        pressure = state.record?.pressure ?: "",
        feelings = state.record?.feelings ?: ""
    )
}
