package com.i.auth_impl.signin.store

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class DefaultSignInDispatchers : SignInDispatchers {
    override val mainContext: CoroutineDispatcher
        get() = Dispatchers.Main.immediate
    override val ioContext: CoroutineDispatcher
        get() = Dispatchers.IO
    override val unconfiedContext: CoroutineDispatcher
        get() = Dispatchers.Unconfined
}
