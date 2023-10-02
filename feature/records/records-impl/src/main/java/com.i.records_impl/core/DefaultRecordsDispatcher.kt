package com.i.records_impl.core

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class DefaultRecordsDispatcher : RecordsDispatchers {
    override val mainContext: CoroutineDispatcher
        get() = Dispatchers.Main.immediate
    override val ioContext: CoroutineDispatcher
        get() = Dispatchers.IO
    override val unconfiedContext: CoroutineDispatcher
        get() = Dispatchers.Unconfined
}
