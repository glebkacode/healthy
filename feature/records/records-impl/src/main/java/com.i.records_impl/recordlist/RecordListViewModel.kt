package com.i.records_impl.recordlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.i.records_impl.core.Record
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RecordListViewModel(
    private val getRecordsListUseCase: GetRecordsListUseCase
) : ViewModel() {

    private val recordsInternal = MutableStateFlow<List<Record>>(emptyList())
    val records = recordsInternal.asStateFlow()

    init {
        getRecords()
    }

    private fun getRecords() {
        viewModelScope.launch(Dispatchers.Default) {
            val records = getRecordsListUseCase()
            withContext(Dispatchers.Main) {
                recordsInternal.emit(records)
            }
        }
    }
}
