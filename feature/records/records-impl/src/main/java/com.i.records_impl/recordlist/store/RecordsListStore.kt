package com.i.records_impl.recordlist.store

import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.utils.JvmSerializable
import com.i.records_impl.core.Record
import com.i.records_impl.recordlist.store.RecordsListStore.Intent
import com.i.records_impl.recordlist.store.RecordsListStore.State

interface RecordsListStore : Store<Intent, State, Nothing> {

    sealed class Intent : JvmSerializable {
        object GetRecords : Intent()
        object AddNewRecord : Intent()
    }

    data class State(
        val records: List<Record> = emptyList()
    ) : JvmSerializable
}
