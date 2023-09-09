package com.i.navigation

import com.i.navigation.ScreenDest.SignInScreenDest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ScreenNavigator : Navigator {
    private val screenInternal: MutableStateFlow<ScreenDest> = MutableStateFlow(SignInScreenDest)
    override val screen: StateFlow<ScreenDest> = screenInternal.asStateFlow()

    override suspend fun navigate(screen: ScreenDest) {
        screenInternal.emit(screen)
    }
}
