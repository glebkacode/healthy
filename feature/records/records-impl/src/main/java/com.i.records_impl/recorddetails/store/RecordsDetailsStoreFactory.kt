package com.i.records_impl.recorddetails.store

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.SimpleBootstrapper
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.core.utils.JvmSerializable
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.i.records_impl.core.Record
import com.i.records_impl.recorddetails.bl.GetRecordByIdUseCase
import com.i.records_impl.recorddetails.store.RecordDetailsStore.Intent
import com.i.records_impl.recorddetails.store.RecordDetailsStore.State
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class RecordsDetailsStoreFactory(
    private val storeFactory: StoreFactory,
    private val getRecordByIdUseCase: GetRecordByIdUseCase,
    private val mainContext: CoroutineContext,
    private val ioContext: CoroutineContext
) {

    fun create(): RecordDetailsStore =
        object : RecordDetailsStore, Store<Intent, State, Nothing> by storeFactory.create(
            name = "RecordDetailsStore",
            initialState = State(),
            bootstrapper = SimpleBootstrapper(Unit),
            executorFactory = ::ExecutorImpl,
            reducer = ReducerImpl
        ) {}

    private inner class ExecutorImpl :
        CoroutineExecutor<Intent, Unit, State, Msg, Nothing>(mainContext) {
        override fun executeAction(action: Unit, getState: () -> State) {
            super.executeAction(action, getState)
        }

        override fun executeIntent(intent: Intent, getState: () -> State) {
            when (intent) {
                is Intent.GetRecordById -> getRecords(intent.id)
            }
        }

        private fun getRecords(id: Long) {
            scope.launch(ioContext) {
                runCatching {
                    getRecordByIdUseCase(id)
                }.onSuccess { records ->
                    dispatch(Msg.GetRecordByIdSuccess(records))
                }.onFailure { throwable ->
                    dispatch(Msg.GetRecordByIdFailed(throwable))
                }
            }
        }
    }

    private object ReducerImpl : Reducer<State, Msg> {
        override fun State.reduce(msg: Msg): State {
            return when (msg) {
                is Msg.GetRecordByIdSuccess -> copy(record = msg.record)
                is Msg.GetRecordByIdFailed -> copy()
            }
        }
    }
}

private sealed interface Msg : JvmSerializable {
    @JvmInline
    value class GetRecordByIdSuccess(val record: Record) : Msg

    @JvmInline
    value class GetRecordByIdFailed(val throwable: Throwable) : Msg
}
