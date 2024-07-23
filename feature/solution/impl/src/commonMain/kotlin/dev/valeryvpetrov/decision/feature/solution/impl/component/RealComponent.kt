package dev.valeryvpetrov.decision.feature.solution.impl.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import dev.valeryvpetrov.decision.base.api.Provider
import dev.valeryvpetrov.decision.feature.solution.api.Component
import dev.valeryvpetrov.decision.feature.solution.api.Intent
import dev.valeryvpetrov.decision.feature.solution.api.Label
import dev.valeryvpetrov.decision.feature.solution.api.SolutionComponent
import dev.valeryvpetrov.decision.feature.solution.api.SolutionComponentFactory
import dev.valeryvpetrov.decision.feature.solution.api.State
import dev.valeryvpetrov.decision.feature.solution.impl.mvi.StoreFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

class RealComponent(
    componentContext: ComponentContext,
    private val onGoToProblem: () -> Unit,
    private val onGoToDecision: () -> Unit,
    storeFactoryProvider: Provider<StoreFactory>,
) : ComponentContext by componentContext, SolutionComponent {

    class Factory(
        private val storeFactoryProvider: Provider<StoreFactory>,
    ) : SolutionComponentFactory {

        override fun create(
            componentContext: ComponentContext,
            onGoToProblem: () -> Unit,
            onGoToDecision: () -> Unit,
        ): Component = RealComponent(
            componentContext = componentContext,
            onGoToProblem = onGoToProblem,
            onGoToDecision = onGoToDecision,
            storeFactoryProvider = storeFactoryProvider,
        )
    }

    private val store: Store<Intent, State, Label> = instanceKeeper.getStore {
        storeFactoryProvider.get().create(stateKeeper)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override val state: StateFlow<State> = store.stateFlow
    override val labels: Flow<Label> = store.labels

    override fun accept(intent: Intent) = store.accept(intent)

    override fun onGoToProblem() = onGoToProblem.invoke()

    override fun onGoToDecision() = onGoToDecision.invoke()
}