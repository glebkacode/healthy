package com.i.records_impl.recorddetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RecordDetailsViewModel(
    private val getRecordsByIdUseCase: GetRecordsByIdUseCase
) : ViewModel() {

    private val recordStateInternal = MutableStateFlow(RecordUiModel())
    val recordState = recordStateInternal.asStateFlow()

    init {
        getRecord()
    }

    private fun getRecord() {
        val id = 1L
        viewModelScope.launch(Dispatchers.Default) {
            val record = getRecordsByIdUseCase(id)
            recordStateInternal.emit(
                RecordUiModel(
                    date = record.date,
                    pressure = record.pressure,
                    feelings = record.feelings
                )
            )
        }
    }
}
