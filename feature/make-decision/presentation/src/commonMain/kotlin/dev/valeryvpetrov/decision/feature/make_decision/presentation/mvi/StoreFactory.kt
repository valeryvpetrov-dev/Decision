package dev.valeryvpetrov.decision.feature.make_decision.presentation.mvi

import com.arkivanov.essenty.statekeeper.StateKeeper
import com.arkivanov.mvikotlin.core.store.Store
import dev.valeryvpetrov.decision.feature.problem.api.Problem
import dev.valeryvpetrov.decision.feature.solution.api.Solution
import com.arkivanov.mvikotlin.core.store.StoreFactory as MviStoreFactory

class StoreFactory(
    private val storeFactory: MviStoreFactory,
    private val storeName: String,
    private val bootstrapper: Bootstrapper,
    private val reducer: Reducer,
    private val executorFactory: Executor.Factory,
) {

    fun create(
        stateKeeper: StateKeeper,
        onGoToSolution: (List<Solution>?) -> Unit,
        onGoToDecision: (String) -> Unit,
        onBackToProblem: (Problem?) -> Unit,
        onRestart: (Problem?) -> Unit,
        onBackToSolution: (List<Solution>?) -> Unit,
    ): Store<MakeDecisionIntent, MakeDecisionState, Nothing> {
        val initialState = stateKeeper.consume(
            key = MakeDecisionState.STATE_KEEPER_KEY, strategy = MakeDecisionState.serializer()
        ) ?: MakeDecisionState.initial()
        val executor = executorFactory.create(
            savedState = initialState,
            onGoToSolution = onGoToSolution,
            onGoToDecision = onGoToDecision,
            onBackToProblem = onBackToProblem,
            onRestart = onRestart,
            onBackToSolution = onBackToSolution,
        )
        return storeFactory.create(
            name = storeName,
            initialState = initialState,
            bootstrapper = bootstrapper,
            reducer = reducer,
            executorFactory = { executor },
        ).also {
            stateKeeper.register(
                key = MakeDecisionState.STATE_KEEPER_KEY, strategy = MakeDecisionState.serializer()
            ) {
                it.state
            }
        }
    }
}