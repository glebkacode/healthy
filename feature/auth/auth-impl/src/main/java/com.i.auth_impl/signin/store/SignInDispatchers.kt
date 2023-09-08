package com.i.auth_impl.signin.store

import kotlinx.coroutines.CoroutineDispatcher

interface SignInDispatchers {
    val mainContext: CoroutineDispatcher
    val ioContext: CoroutineDispatcher
    val unconfiedContext: CoroutineDispatcher
}