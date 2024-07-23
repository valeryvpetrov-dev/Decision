package dev.valeryvpetrov.decision.feature.problem.impl.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import dev.valeryvpetrov.decision.base.api.Provider
import dev.valeryvpetrov.decision.feature.problem.api.Component
import dev.valeryvpetrov.decision.feature.problem.api.Intent
import dev.valeryvpetrov.decision.feature.problem.api.Label
import dev.valeryvpetrov.decision.feature.problem.api.ProblemComponent
import dev.valeryvpetrov.decision.feature.problem.api.ProblemComponentFactory
import dev.valeryvpetrov.decision.feature.problem.api.State
import dev.valeryvpetrov.decision.feature.problem.impl.mvi.StoreFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

class RealComponent(
    componentContext: ComponentContext,
    private val onGoToSolutions: () -> Unit,
    storeFactoryProvider: Provider<StoreFactory>,
) : ComponentContext by componentContext, ProblemComponent {

    class Factory(
        private val storeFactoryProvider: Provider<StoreFactory>,
    ) : ProblemComponentFactory {

        override fun create(
            componentContext: ComponentContext,
            onGoToSolutions: () -> Unit
        ): Component = RealComponent(
            componentContext = componentContext,
            onGoToSolutions = onGoToSolutions,
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

    override fun onGoToSolutions() = onGoToSolutions.invoke()
}