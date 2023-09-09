package com.i.records_impl.recorddetails.store

import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.utils.JvmSerializable
import com.i.records_impl.core.Record
import com.i.records_impl.recorddetails.store.RecordDetailsStore.Intent
import com.i.records_impl.recorddetails.store.RecordDetailsStore.State

interface RecordDetailsStore : Store<Intent, State, Nothing> {

    sealed class Intent : JvmSerializable {
        data class GetRecordById(val id: Long) : Intent()
    }

    data class State(
        val record: Record? = null
    ) : JvmSerializable
}
