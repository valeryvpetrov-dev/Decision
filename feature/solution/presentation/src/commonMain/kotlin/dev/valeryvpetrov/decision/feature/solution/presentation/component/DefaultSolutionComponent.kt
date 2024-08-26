package dev.valeryvpetrov.decision.feature.solution.presentation.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.backhandler.BackCallback
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.core.store.Store
import dev.valeryvpetrov.decision.base.api.Provider
import dev.valeryvpetrov.decision.feature.solution.api.Solution
import dev.valeryvpetrov.decision.feature.solution.presentation.mvi.SolutionIntent
import dev.valeryvpetrov.decision.feature.solution.presentation.mvi.SolutionLabel
import dev.valeryvpetrov.decision.feature.solution.presentation.mvi.SolutionState
import dev.valeryvpetrov.decision.feature.solution.presentation.mvi.StoreFactory

class DefaultSolutionComponent(
    componentContext: ComponentContext,
    private val solutions: List<Solution>?,
    private val onBackToProblem: (List<Solution>) -> Unit,
    private val onGoToDecision: (List<Solution>) -> Unit,
    storeFactoryProvider: Provider<StoreFactory>,
) : ComponentContext by componentContext, SolutionComponent(
    componentContext = componentContext
) {

    class Factory(
        private val storeFactoryProvider: Provider<StoreFactory>,
    ) : SolutionComponent.Factory {

        override fun create(
            componentContext: ComponentContext,
            solutions: List<Solution>?,
            onBackToProblem: (List<Solution>) -> Unit,
            onGoToDecision: (List<Solution>) -> Unit,
        ): SolutionComponent = DefaultSolutionComponent(
            componentContext = componentContext,
            solutions = solutions,
            onBackToProblem = onBackToProblem,
            onGoToDecision = onGoToDecision,
            storeFactoryProvider = storeFactoryProvider,
        )
    }

    override val store: Store<SolutionIntent, SolutionState, SolutionLabel> =
        instanceKeeper.getStore {
        storeFactoryProvider.get().create(
            stateKeeper = stateKeeper,
            solutions = solutions,
            onBackToProblem = onBackToProblem,
            onGoToDecision = onGoToDecision,
        )
    }

    private val backCallback = BackCallback {
        store.accept(SolutionIntent.Back)
    }

    init {
        backHandler.register(backCallback)
    }
}