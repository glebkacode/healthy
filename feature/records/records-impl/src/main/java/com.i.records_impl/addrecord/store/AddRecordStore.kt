package com.i.records_impl.addrecord.store

import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.utils.JvmSerializable
import com.i.records_impl.addrecord.store.AddRecordStore.Intent
import com.i.records_impl.addrecord.store.AddRecordStore.State

interface AddRecordStore : Store<Intent, State, Nothing> {

    sealed class Intent : JvmSerializable {
        data class DateChanged(val text: String) : Intent()
        data class PressureChanged(val text: String) : Intent()
        data class FeelingsChanged(val text: String) : Intent()
        object AddNewRecord : Intent()
    }

    data class State(
        val date: String = "",
        val pressure: String = "",
        val feelings: String = ""
    ) : JvmSerializable
}
