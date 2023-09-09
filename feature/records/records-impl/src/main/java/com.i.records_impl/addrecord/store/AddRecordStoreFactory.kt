package com.i.records_impl.addrecord.store

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.SimpleBootstrapper
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.core.utils.JvmSerializable
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.i.records_impl.addrecord.bl.AddRecordUseCase
import com.i.records_impl.addrecord.store.AddRecordStore.Intent.AddNewRecord
import com.i.records_impl.addrecord.store.AddRecordStore.Intent.DateChanged
import com.i.records_impl.addrecord.store.AddRecordStore.Intent.FeelingsChanged
import com.i.records_impl.addrecord.store.AddRecordStore.Intent.PressureChanged
import com.i.records_impl.core.Record
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class AddRecordStoreFactory(
    private val storeFactory: StoreFactory,
    private val addRecordUseCase: AddRecordUseCase,
    private val mainContext: CoroutineContext,
    private val ioContext: CoroutineContext
) {

    fun create(): AddRecordStore =
        object : AddRecordStore, Store<AddRecordStore.Intent, AddRecordStore.State, Nothing> by storeFactory.create(
            name = "AddRecordStore",
            initialState = AddRecordStore.State(),
            bootstrapper = SimpleBootstrapper(Unit),
            executorFactory = ::ExecutorImpl,
            reducer = ReducerImpl
        ) {}

    private inner class ExecutorImpl :
        CoroutineExecutor<AddRecordStore.Intent, Unit, AddRecordStore.State, Msg, Nothing>(mainContext) {
        override fun executeAction(action: Unit, getState: () -> AddRecordStore.State) {
            super.executeAction(action, getState)
        }

        override fun executeIntent(intent: AddRecordStore.Intent, getState: () -> AddRecordStore.State) {
            when (intent) {
                is DateChanged -> validateDate(intent.text)
                is PressureChanged -> validatePressure(intent.text)
                is FeelingsChanged -> validateFeelings(intent.text)
                is AddNewRecord -> {
                    val state = getState()
                    addNewRecord(state.date, state.pressure, state.feelings)
                }
            }
        }

        private fun validateDate(date: String) {
            if (date.isNotEmpty()) {
                dispatch(Msg.DateChanged(date))
            }
        }

        private fun validatePressure(pressure: String) {
            if (pressure.isNotEmpty()) {
                dispatch(Msg.PressureChanged(pressure))
            }
        }

        private fun validateFeelings(feelings: String) {
            if (feelings.isNotEmpty()) {
                dispatch(Msg.FeelingsChanged(feelings))
            }
        }

        private fun addNewRecord(
            date: String,
            pressure: String,
            feelings: String
        ) {
            scope.launch(ioContext) {
                val record = Record(null, date, pressure, feelings)
                runCatching {
                    addRecordUseCase(record)
                }.onSuccess {
                    dispatch(Msg.AddRecordSuccess)
                }.onFailure { throwable ->
                    dispatch(Msg.AddRecordFailed(throwable))
                }
            }
        }
    }

    private object ReducerImpl : Reducer<AddRecordStore.State, Msg> {
        override fun AddRecordStore.State.reduce(msg: Msg): AddRecordStore.State {
            return when (msg) {
                is Msg.DateChanged -> copy(date = msg.date)
                is Msg.PressureChanged -> copy(pressure = msg.pressure)
                is Msg.FeelingsChanged -> copy(feelings = msg.feelings)
                is Msg.AddRecordSuccess -> copy()
                is Msg.AddRecordFailed -> copy()
            }
        }
    }
}

private sealed interface Msg : JvmSerializable {
    @JvmInline
    value class DateChanged(val date: String) : Msg
    @JvmInline
    value class PressureChanged(val pressure: String) : Msg
    @JvmInline
    value class FeelingsChanged(val feelings: String) : Msg
    object AddRecordSuccess : Msg
    @JvmInline
    value class AddRecordFailed(val throwable: Throwable) : Msg
}