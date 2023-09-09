package com.i.records_impl.recordlist.component

import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import com.i.core_architecture.component.Component
import com.i.records_impl.core.RecordsDispatchers
import com.i.records_impl.recordlist.bl.GetRecordsListUseCase
import com.i.records_impl.recordlist.store.RecordsListFactory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RecordsListComponent(
    private val storeFactory: StoreFactory,
    private val getRecordsListUseCase: GetRecordsListUseCase,
    private val recordsDispatchers: RecordsDispatchers,
    instanceKeeper: InstanceKeeper
) : Component<RecordsUiModel, Event> {

    private val recordsListStore = instanceKeeper.getStore {
        RecordsListFactory(
            storeFactory = storeFactory,
            getRecordsListUseCase,
            mainContext = recordsDispatchers.mainContext,
            ioContext = recordsDispatchers.ioContext
        ).create()
    }

    override val ui: Flow<RecordsUiModel> = recordsListStore.stateFlow.map(::stateToUi)

    override fun dispatch(event: Event) {}
}