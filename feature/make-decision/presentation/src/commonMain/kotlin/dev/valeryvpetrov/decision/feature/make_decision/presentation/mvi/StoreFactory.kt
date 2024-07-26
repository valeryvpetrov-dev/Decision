package dev.valeryvpetrov.decision.feature.make_decision.presentation.mvi

import com.arkivanov.essenty.statekeeper.StateKeeper
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory as MviStoreFactory

class StoreFactory(
    private val storeFactory: MviStoreFactory,
    private val storeName: String,
    private val bootstrapper: Bootstrapper,
    private val reducer: Reducer,
    private val executorFactory: Executor.Factory,
) {

    fun create(stateKeeper: StateKeeper): Store<Intent, State, Label> {
        val initialState = stateKeeper.consume(
            key = State.STATE_KEEPER_KEY, strategy = State.serializer()
        ) ?: State.initial()
        val executor = executorFactory.create(initialState)
        return storeFactory.create(
            name = storeName,
            initialState = initialState,
            bootstrapper = bootstrapper,
            reducer = reducer,
            executorFactory = { executor },
        ).also {
            stateKeeper.register(
                key = State.STATE_KEEPER_KEY, strategy = State.serializer()
            ) {
                it.state
            }
        }
    }
}