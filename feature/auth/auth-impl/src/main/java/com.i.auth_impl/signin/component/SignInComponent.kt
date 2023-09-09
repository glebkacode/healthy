package com.i.auth_impl.signin.component

import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import com.i.auth_impl.signin.bl.AuthUseCase
import com.i.auth_impl.signin.store.SignInDispatchers
import com.i.auth_impl.signin.store.SignInStore
import com.i.auth_impl.signin.store.SignInStoreFactory
import com.i.core_architecture.component.Component
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SignInComponent(
    private val storeFactory: StoreFactory,
    private val authUseCase: AuthUseCase,
    private val dispatchers: SignInDispatchers,
    instanceKeeper: InstanceKeeper
) : Component<SignInUiModel, Event> {

    private val signInStore = instanceKeeper.getStore {
        SignInStoreFactory(
            storeFactory = storeFactory,
            authUseCase = authUseCase,
            mainCoroutineContext = dispatchers.mainContext,
            ioCoroutineContext = dispatchers.ioContext,
        ).create()
    }

    override val ui: Flow<SignInUiModel> = signInStore.stateFlow.map(::stateToUi)

    override fun dispatch(event: Event) {
        when(event) {
            is Event.EmailTextInput -> {
                signInStore.accept(SignInStore.Intent.ChangeEmail(event.email))
            }
            is Event.PasswordTextInput -> {
                signInStore.accept(SignInStore.Intent.ChangePassword(event.password))
            }
            is Event.SignInClicked -> {
                signInStore.accept(SignInStore.Intent.SignIn)
            }
        }
    }
}