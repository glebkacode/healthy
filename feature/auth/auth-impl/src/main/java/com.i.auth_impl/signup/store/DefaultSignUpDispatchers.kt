package com.i.auth_impl.signup.store

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class DefaultSignUpDispatchers : SignUpDispatchers {
    override val mainContext: CoroutineDispatcher
        get() = Dispatchers.Main.immediate
    override val ioContext: CoroutineDispatcher
        get() = Dispatchers.IO
    override val unconfiedContext: CoroutineDispatcher
        get() = Dispatchers.Unconfined
}
