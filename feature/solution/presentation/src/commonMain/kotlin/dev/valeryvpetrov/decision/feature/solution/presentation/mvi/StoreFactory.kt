package dev.valeryvpetrov.decision.feature.solution.presentation.mvi

import com.arkivanov.essenty.statekeeper.StateKeeper
import com.arkivanov.mvikotlin.core.store.Store
import dev.valeryvpetrov.decision.feature.solution.api.Solution
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
    ): Store<SolutionIntent, SolutionState, SolutionLabel> {
        val initialState = stateKeeper.consume(
            key = SolutionState.STATE_KEEPER_KEY, strategy = SolutionState.serializer()
        ) ?: SolutionState.initial()
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
                key = SolutionState.STATE_KEEPER_KEY, strategy = SolutionState.serializer()
            ) {
                it.state
            }
            it.accept(SolutionIntent.Restore(solutions))
        }
    }
}