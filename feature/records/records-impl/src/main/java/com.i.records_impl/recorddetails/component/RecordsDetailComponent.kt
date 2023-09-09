package com.i.records_impl.recorddetails.component

import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import com.i.core_architecture.component.Component
import com.i.records_impl.core.RecordsDispatchers
import com.i.records_impl.recorddetails.bl.GetRecordByIdUseCase
import com.i.records_impl.recorddetails.store.RecordsDetailsStoreFactory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RecordsDetailComponent(
    private val storeFactory: StoreFactory,
    private val getRecordByIdUseCase: GetRecordByIdUseCase,
    private val recordsDispatchers: RecordsDispatchers,
    instanceKeeper: InstanceKeeper
) : Component<RecordUiModel, Event> {

    private val recordsListStore = instanceKeeper.getStore {
        RecordsDetailsStoreFactory(
            storeFactory = storeFactory,
            getRecordByIdUseCase,
            mainContext = recordsDispatchers.mainContext,
            ioContext = recordsDispatchers.ioContext
        ).create()
    }

    override val ui: Flow<RecordUiModel> = recordsListStore.stateFlow.map(::stateToUi)

    override fun dispatch(event: Event) {}
}