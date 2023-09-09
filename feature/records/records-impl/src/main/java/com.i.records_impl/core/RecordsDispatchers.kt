package com.i.records_impl.core

import kotlinx.coroutines.CoroutineDispatcher

interface RecordsDispatchers {
    val mainContext: CoroutineDispatcher
    val ioContext: CoroutineDispatcher
    val unconfiedContext: CoroutineDispatcher
}