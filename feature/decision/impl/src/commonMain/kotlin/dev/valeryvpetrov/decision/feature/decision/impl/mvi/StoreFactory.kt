package dev.valeryvpetrov.decision.feature.decision.impl.mvi

import com.arkivanov.essenty.statekeeper.StateKeeper
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import dev.valeryvpetrov.decision.base.impl.mvi.BaseStoreFactory
import dev.valeryvpetrov.decision.feature.decision.api.Intent
import dev.valeryvpetrov.decision.feature.decision.api.Label
import dev.valeryvpetrov.decision.feature.decision.api.State

class StoreFactory(
    private val storeFactory: StoreFactory,
    private val storeName: String,
    private val executor: Executor,
    private val reducer: Reducer,
    private val bootstrapper: Bootstrapper,
) : BaseStoreFactory<Intent, State, Label> {

    override fun create(stateKeeper: StateKeeper): Store<Intent, State, Label> {
        val initialState = stateKeeper.consume(
            key = State.STATE_KEEPER_KEY, strategy = State.serializer()
        ) ?: State.initial()
        return storeFactory.create(
            name = storeName,
            initialState = initialState,
            bootstrapper = bootstrapper,
            executorFactory = { executor },
            reducer = reducer
        ).also {
            stateKeeper.register(
                key = State.STATE_KEEPER_KEY, strategy = State.serializer()
            ) {
                it.state
            }
        }
    }
}