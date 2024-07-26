package dev.valeryvpetrov.decision.feature.problem.presentation.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import dev.valeryvpetrov.decision.base.api.Provider
import dev.valeryvpetrov.decision.feature.problem.api.Problem
import dev.valeryvpetrov.decision.feature.problem.presentation.mvi.Intent
import dev.valeryvpetrov.decision.feature.problem.presentation.mvi.State
import dev.valeryvpetrov.decision.feature.problem.presentation.mvi.StoreFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow

class RealComponent(
    private val componentContext: ComponentContext,
    private val problem: Problem?,
    private val onGoToSolutions: (Problem) -> Unit,
    private val storeFactoryProvider: Provider<StoreFactory>,
) : ComponentContext by componentContext, ProblemComponent {

    class Factory(
        private val storeFactoryProvider: Provider<StoreFactory>,
    ) : ProblemComponentFactory {

        override fun create(
            componentContext: ComponentContext,
            problem: Problem?,
            onGoToSolutions: (Problem) -> Unit,
        ): Component = RealComponent(
            componentContext = componentContext,
            problem = problem,
            onGoToSolutions = onGoToSolutions,
            storeFactoryProvider = storeFactoryProvider,
        )
    }

    private val store: Store<Intent, State, Nothing> = instanceKeeper.getStore {
        storeFactoryProvider.get().create(
            stateKeeper = stateKeeper,
            problem = problem,
            onGoToSolutions = onGoToSolutions,
        )
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override val state: StateFlow<State> = store.stateFlow

    override fun accept(intent: Intent) = store.accept(intent)
}