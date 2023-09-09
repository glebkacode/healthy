package com.i.records_impl.addrecord.component

import com.i.records_impl.addrecord.store.AddRecordStore

internal fun stateToUi(state: AddRecordStore.State): AddRecordUiModel {
    return AddRecordUiModel(
        date = state.date,
        pressure = state.pressure,
        feelings = state.feelings
    )
}