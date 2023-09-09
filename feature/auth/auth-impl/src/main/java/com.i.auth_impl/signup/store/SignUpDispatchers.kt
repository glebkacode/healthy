package com.i.auth_impl.signup.store

import kotlinx.coroutines.CoroutineDispatcher

interface SignUpDispatchers {
    val mainContext: CoroutineDispatcher
    val ioContext: CoroutineDispatcher
    val unconfiedContext: CoroutineDispatcher
}
