package dev.valeryvpetrov.decision.feature.decision.impl.mvi

import com.arkivanov.essenty.statekeeper.StateKeeper
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import dev.valeryvpetrov.decision.feature.decision.api.Intent
import dev.valeryvpetrov.decision.feature.decision.api.State

class StoreFactory(
    private val storeFactory: StoreFactory,
    private val storeName: String,
    private val executorFactory: Executor.Factory,
    private val reducer: Reducer,
) {

    fun create(
        stateKeeper: StateKeeper,
        decisionMessage: String,
        onGoToSolutions: () -> Unit,
        onRestart: () -> Unit,
    ): Store<Intent, State, Nothing> {
        val initialState = stateKeeper.consume(
            key = State.STATE_KEEPER_KEY, strategy = State.serializer()
        ) ?: State.initial()
        return storeFactory.create(
            name = storeName,
            initialState = initialState,
            executorFactory = {
                executorFactory.create(
                    onGoToSolutions = onGoToSolutions,
                    onRestart = onRestart,
                )
            },
            reducer = reducer
        ).also {
            stateKeeper.register(
                key = State.STATE_KEEPER_KEY, strategy = State.serializer()
            ) {
                it.state
            }
            it.accept(Intent.Restore(decisionMessage))
        }
    }
}