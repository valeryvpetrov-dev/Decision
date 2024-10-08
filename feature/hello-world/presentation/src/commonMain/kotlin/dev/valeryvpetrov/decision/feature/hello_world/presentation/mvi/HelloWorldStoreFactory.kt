package dev.valeryvpetrov.decision.feature.hello_world.presentation.mvi

import com.arkivanov.essenty.statekeeper.StateKeeper
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory as MviStoreFactory

class HelloWorldStoreFactory(
    private val storeName: String,
    private val storeFactory: MviStoreFactory,
    private val executor: HelloWorldExecutor,
    private val reducer: HelloWorldReducer,
) {

    fun create(
        stateKeeper: StateKeeper,
    ): Store<HelloWorldIntent, HelloWorldState, Nothing> {
        val initialState = stateKeeper.consume(
            key = HelloWorldState.STATE_KEEPER_KEY, strategy = HelloWorldState.serializer()
        ) ?: HelloWorldState.initial()
        return storeFactory.create(
            name = storeName,
            initialState = initialState,
            executorFactory = { executor },
            reducer = reducer,
        ).also {
            stateKeeper.register(
                key = HelloWorldState.STATE_KEEPER_KEY, strategy = HelloWorldState.serializer()
            ) {
                it.state
            }
        }
    }
}