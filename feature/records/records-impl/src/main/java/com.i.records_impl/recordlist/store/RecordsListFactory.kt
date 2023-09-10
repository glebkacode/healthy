package com.i.records_impl.recordlist.store

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.SimpleBootstrapper
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.core.utils.JvmSerializable
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.i.navigation.Navigator
import com.i.navigation.ScreenDest
import com.i.records_impl.core.Record
import com.i.records_impl.recordlist.bl.GetRecordsListUseCase
import com.i.records_impl.recordlist.store.RecordsListStore.Intent
import com.i.records_impl.recordlist.store.RecordsListStore.State
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class RecordsListFactory(
    private val storeFactory: StoreFactory,
    private val getRecordsListUseCase: GetRecordsListUseCase,
    private val navigator: Navigator,
    private val mainContext: CoroutineContext,
    private val ioContext: CoroutineContext
) {

    fun create(): RecordsListStore =
        object : RecordsListStore, Store<Intent, State, Nothing> by storeFactory.create(
            name = "RecordsListStore",
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
                is Intent.GetRecords -> getRecords()
                is Intent.AddNewRecord -> addNewRecord()
            }
        }

        private fun getRecords() {
            scope.launch(ioContext) {
                runCatching {
                    getRecordsListUseCase()
                }.onSuccess { records ->
                    dispatch(Msg.GetRecordsSuccess(records))
                }.onFailure { throwable ->
                    dispatch(Msg.GetRecordsFailed(throwable))
                }
            }
        }

        private fun addNewRecord() {
            scope.launch(ioContext) {
                navigator.navigate(ScreenDest.AddRecordScreenDest)
            }
        }
    }

    private object ReducerImpl : Reducer<State, Msg> {
        override fun State.reduce(msg: Msg): State {
            return when (msg) {
                is Msg.GetRecordsSuccess -> copy(records = msg.records)
                is Msg.GetRecordsFailed -> copy()
            }
        }
    }
}

private sealed interface Msg : JvmSerializable {
    @JvmInline
    value class GetRecordsSuccess(val records: List<Record>) : Msg

    @JvmInline
    value class GetRecordsFailed(val throwable: Throwable) : Msg
}
