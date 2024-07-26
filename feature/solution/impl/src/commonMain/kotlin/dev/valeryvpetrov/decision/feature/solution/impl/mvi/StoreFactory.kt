package dev.valeryvpetrov.decision.feature.solution.impl.mvi

import com.arkivanov.essenty.statekeeper.StateKeeper
import com.arkivanov.mvikotlin.core.store.Store
import dev.valeryvpetrov.decision.domain.Solution
import dev.valeryvpetrov.decision.feature.solution.api.Intent
import dev.valeryvpetrov.decision.feature.solution.api.State
import com.arkivanov.mvikotlin.core.store.StoreFactory as MviStoreFactory

class StoreFactory(
    private val storeFactory: MviStoreFactory,
    private val storeName: String,
    private val executorFactory: Executor.Factory,
    private val reducer: Reducer,
) {

    fun create(
        stateKeeper: StateKeeper,
        solutions: List<Solution>?,
        onBackToProblem: (List<Solution>) -> Unit,
        onGoToDecision: (List<Solution>) -> Unit,
    ): Store<Intent, State, Nothing> {
        val initialState = stateKeeper.consume(
            key = State.STATE_KEEPER_KEY, strategy = State.serializer()
        ) ?: State.initial()
        return storeFactory.create(
            name = storeName,
            initialState = initialState,
            executorFactory = {
                executorFactory.create(
                    onBackToProblem = onBackToProblem,
                    onGoToDecision = onGoToDecision,
                )
            },
            reducer = reducer,
        ).also {
            stateKeeper.register(
                key = State.STATE_KEEPER_KEY, strategy = State.serializer()
            ) {
                it.state
            }
            it.accept(Intent.Restore(solutions))
        }
    }
}