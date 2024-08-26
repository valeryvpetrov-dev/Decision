package dev.valeryvpetrov.decision.feature.decision.presentation.mvi

import com.arkivanov.essenty.statekeeper.StateKeeper
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory

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
    ): Store<DecisionIntent, DecisionState, Nothing> {
        val initialState = stateKeeper.consume(
            key = DecisionState.STATE_KEEPER_KEY, strategy = DecisionState.serializer()
        ) ?: DecisionState.initial()
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
                key = DecisionState.STATE_KEEPER_KEY, strategy = DecisionState.serializer()
            ) {
                it.state
            }
            it.accept(DecisionIntent.Restore(decisionMessage))
        }
    }
}