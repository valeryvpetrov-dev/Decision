package dev.valeryvpetrov.decision.feature.problem.presentation.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.core.store.Store
import dev.valeryvpetrov.decision.base.api.Provider
import dev.valeryvpetrov.decision.feature.problem.api.Problem
import dev.valeryvpetrov.decision.feature.problem.presentation.mvi.ProblemIntent
import dev.valeryvpetrov.decision.feature.problem.presentation.mvi.ProblemState
import dev.valeryvpetrov.decision.feature.problem.presentation.mvi.StoreFactory

class DefaultProblemComponent(
    componentContext: ComponentContext,
    private val problem: Problem?,
    private val onGoToSolutions: (Problem) -> Unit,
    private val storeFactoryProvider: Provider<StoreFactory>,
) : ComponentContext by componentContext, ProblemComponent(
    componentContext = componentContext
) {

    class Factory(
        private val storeFactoryProvider: Provider<StoreFactory>,
    ) : ProblemComponent.Factory {

        override fun create(
            componentContext: ComponentContext,
            problem: Problem?,
            onGoToSolutions: (Problem) -> Unit,
        ): ProblemComponent = DefaultProblemComponent(
            componentContext = componentContext,
            problem = problem,
            onGoToSolutions = onGoToSolutions,
            storeFactoryProvider = storeFactoryProvider,
        )
    }

    override val store: Store<ProblemIntent, ProblemState, Nothing> = instanceKeeper.getStore {
        storeFactoryProvider.get().create(
            stateKeeper = stateKeeper,
            problem = problem,
            onGoToSolutions = onGoToSolutions,
        )
    }
}