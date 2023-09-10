package com.i.core_architecture.component

import kotlinx.coroutines.flow.Flow

interface Component<I, E> {
    val ui: Flow<I>
    fun dispatch(event: E)
}
