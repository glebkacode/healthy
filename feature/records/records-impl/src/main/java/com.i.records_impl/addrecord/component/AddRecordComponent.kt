package com.i.records_impl.addrecord.component

import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import com.i.core_architecture.component.Component
import com.i.records_impl.addrecord.bl.AddRecordUseCase
import com.i.records_impl.addrecord.store.AddRecordStore.Intent.AddNewRecord
import com.i.records_impl.addrecord.store.AddRecordStore.Intent.DateChanged
import com.i.records_impl.addrecord.store.AddRecordStore.Intent.FeelingsChanged
import com.i.records_impl.addrecord.store.AddRecordStore.Intent.PressureChanged
import com.i.records_impl.addrecord.store.AddRecordStoreFactory
import com.i.records_impl.core.RecordsDispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AddRecordComponent(
    private val storeFactory: StoreFactory,
    private val addRecordUseCase: AddRecordUseCase,
    private val recordsDispatchers: RecordsDispatchers,
    instanceKeeper: InstanceKeeper
) : Component<AddRecordUiModel, Event> {

    private val addRecordStore = instanceKeeper.getStore {
        AddRecordStoreFactory(
            storeFactory = storeFactory,
            addRecordUseCase,
            mainContext = recordsDispatchers.mainContext,
            ioContext = recordsDispatchers.ioContext
        ).create()
    }

    override val ui: Flow<AddRecordUiModel> = addRecordStore.stateFlow.map(::stateToUi)

    override fun dispatch(event: Event) {
        when (event) {
            is Event.DateTextChanged -> addRecordStore.accept(DateChanged(event.text))
            is Event.PressureTextChanged -> addRecordStore.accept(PressureChanged(event.text))
            is Event.FeelingsTextChanged -> addRecordStore.accept(FeelingsChanged(event.text))
            is Event.AddRecordClicked -> addRecordStore.accept(AddNewRecord)
        }
    }
}
