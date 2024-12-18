package dev.valeryvpetrov.decision.feature.problem.presentation.mvi

import com.arkivanov.essenty.statekeeper.StateKeeper
import com.arkivanov.mvikotlin.core.store.Store
import dev.valeryvpetrov.decision.feature.problem.api.Problem
import com.arkivanov.mvikotlin.core.store.StoreFactory as MviStoreFactory

class StoreFactory(
    private val storeFactory: MviStoreFactory,
    private val storeName: String,
    private val executorFactory: Executor.Factory,
    private val reducer: Reducer,
) {

    fun create(
        stateKeeper: StateKeeper,
        initialState: ProblemState,
        problem: Problem?,
        onGoToSolutions: (Problem) -> Unit,
    ): Store<ProblemIntent, ProblemState, Nothing> {
        val state = stateKeeper.consume(
            key = ProblemState.STATE_KEEPER_KEY, strategy = ProblemState.serializer()
        ) ?: initialState
        return storeFactory.create(
            name = storeName,
            initialState = state,
            executorFactory = {
                executorFactory.create(onGoToSolutions)
            },
            reducer = reducer
        ).also {
            stateKeeper.register(
                key = ProblemState.STATE_KEEPER_KEY, strategy = ProblemState.serializer()
            ) {
                it.state
            }
            it.accept(ProblemIntent.Restore(problem))
        }
    }
}