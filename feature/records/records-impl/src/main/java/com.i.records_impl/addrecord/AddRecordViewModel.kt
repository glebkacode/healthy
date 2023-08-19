package com.i.records_impl.addrecord

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.i.records_impl.core.Record
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddRecordViewModel(
    private val addRecordUseCase: AddRecordUseCase
) : ViewModel() {

    fun onAddRecordsClicked(
        date: String,
        pressure: String,
        feelings: String
    ) {
        val record = Record(null, date, pressure, feelings)
        viewModelScope.launch(Dispatchers.Default) {
            addRecordUseCase(record)
        }
    }
}
