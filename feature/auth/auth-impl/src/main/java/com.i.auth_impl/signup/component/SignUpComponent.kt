package com.i.auth_impl.signup.component

import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import com.i.auth_impl.signup.store.SignUpDispatchers
import com.i.auth_impl.signup.store.SignUpStore
import com.i.auth_impl.signup.store.SignUpStoreFactory
import com.i.core_architecture.component.Component
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SignUpComponent(
    private val storeFactory: StoreFactory,
    private val dispatchers: SignUpDispatchers,
    instanceKeeper: InstanceKeeper
) : Component<SignUpUiModel, Event> {

    private val signUpStore: SignUpStore = instanceKeeper.getStore {
        SignUpStoreFactory(
            storeFactory = storeFactory,
            mainCoroutineContext = dispatchers.mainContext
        ).create()
    }

    override val ui: Flow<SignUpUiModel> = signUpStore.stateFlow.map(::stateToUi)

    override fun dispatch(event: Event) {
        when(event) {
            is Event.EmailTextInput -> {
                signUpStore.accept(SignUpStore.Intent.EmailChanged(event.email))
            }
            is Event.PasswordTextInput -> {
                signUpStore.accept(SignUpStore.Intent.PasswordChanged(event.password))
            }
            is Event.SignUpClicked -> {
                signUpStore.accept(SignUpStore.Intent.SignUpUser)
            }
        }
    }
}