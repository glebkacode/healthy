package com.i.navigation

import kotlinx.coroutines.flow.StateFlow

interface Navigator {
    val screen: StateFlow<ScreenDest>
    suspend fun navigate(screen: ScreenDest)
}
