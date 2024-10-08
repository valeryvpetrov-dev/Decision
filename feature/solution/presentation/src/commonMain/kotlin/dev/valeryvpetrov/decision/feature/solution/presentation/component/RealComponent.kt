package dev.valeryvpetrov.decision.feature.solution.presentation.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.backhandler.BackCallback
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.core.store.Store
import dev.valeryvpetrov.decision.base.api.Provider
import dev.valeryvpetrov.decision.feature.solution.api.Solution
import dev.valeryvpetrov.decision.feature.solution.presentation.mvi.Intent
import dev.valeryvpetrov.decision.feature.solution.presentation.mvi.Label
import dev.valeryvpetrov.decision.feature.solution.presentation.mvi.State
import dev.valeryvpetrov.decision.feature.solution.presentation.mvi.StoreFactory

class RealComponent(
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
    ) : SolutionComponentFactory {

        override fun create(
            componentContext: ComponentContext,
            solutions: List<Solution>?,
            onBackToProblem: (List<Solution>) -> Unit,
            onGoToDecision: (List<Solution>) -> Unit,
        ): Component = RealComponent(
            componentContext = componentContext,
            solutions = solutions,
            onBackToProblem = onBackToProblem,
            onGoToDecision = onGoToDecision,
            storeFactoryProvider = storeFactoryProvider,
        )
    }

    override val store: Store<Intent, State, Label> = instanceKeeper.getStore {
        storeFactoryProvider.get().create(
            stateKeeper = stateKeeper,
            solutions = solutions,
            onBackToProblem = onBackToProblem,
            onGoToDecision = onGoToDecision,
        )
    }

    private val backCallback = BackCallback {
        store.accept(Intent.Back)
    }

    init {
        backHandler.register(backCallback)
    }
}