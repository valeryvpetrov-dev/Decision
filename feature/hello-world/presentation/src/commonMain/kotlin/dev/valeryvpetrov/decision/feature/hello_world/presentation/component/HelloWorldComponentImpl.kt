package dev.valeryvpetrov.decision.feature.hello_world.presentation.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import dev.valeryvpetrov.decision.base.api.Provider
import dev.valeryvpetrov.decision.feature.hello_world.presentation.mvi.HelloWorldIntent
import dev.valeryvpetrov.decision.feature.hello_world.presentation.mvi.HelloWorldState
import dev.valeryvpetrov.decision.feature.hello_world.presentation.mvi.HelloWorldStoreFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow

class HelloWorldComponentImpl(
    componentContext: ComponentContext,
    storeFactoryProvider: Provider<HelloWorldStoreFactory>,
) : ComponentContext by componentContext, HelloWorldComponent(
    componentContext = componentContext
) {

    class Factory(
        private val storeFactoryProvider: Provider<HelloWorldStoreFactory>,
    ) : HelloWorldComponent.Factory {

        override fun create(
            componentContext: ComponentContext,
        ): HelloWorldComponent = HelloWorldComponentImpl(
            componentContext = componentContext,
            storeFactoryProvider = storeFactoryProvider,
        )
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    override val state: StateFlow<HelloWorldState> by lazy { store.stateFlow }

    override fun accept(intent: HelloWorldIntent) = store.accept(intent)

    override val store: Store<HelloWorldIntent, HelloWorldState, Nothing> =
        instanceKeeper.getStore {
            storeFactoryProvider.get().create(
                stateKeeper = stateKeeper
            )
        }
}