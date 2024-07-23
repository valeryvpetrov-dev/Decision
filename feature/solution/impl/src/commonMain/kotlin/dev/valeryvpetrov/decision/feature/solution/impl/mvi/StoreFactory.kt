package dev.valeryvpetrov.decision.feature.solution.impl.mvi

import com.arkivanov.essenty.statekeeper.StateKeeper
import com.arkivanov.mvikotlin.core.store.Store
import dev.valeryvpetrov.decision.feature.solution.api.Intent
import dev.valeryvpetrov.decision.feature.solution.api.Label
import dev.valeryvpetrov.decision.feature.solution.api.State
import com.arkivanov.mvikotlin.core.store.StoreFactory as MviStoreFactory

class StoreFactory(
    private val storeFactory: MviStoreFactory,
    private val storeName: String,
    private val executor: Executor,
    private val reducer: Reducer,
    private val bootstrapper: Bootstrapper,
) {

    fun create(stateKeeper: StateKeeper): Store<Intent, State, Label> {
        val initialState = stateKeeper.consume(
            key = State.STATE_KEEPER_KEY, strategy = State.serializer()
        ) ?: State.initial()
        return storeFactory.create(
            name = storeName,
            initialState = initialState,
            executorFactory = { executor },
            reducer = reducer,
            bootstrapper = bootstrapper,
        ).also {
            stateKeeper.register(
                key = State.STATE_KEEPER_KEY, strategy = State.serializer()
            ) {
                it.state
            }
        }
    }
}