package dev.valeryvpetrov.decision.feature.decision.impl.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import dev.valeryvpetrov.decision.base.api.Provider
import dev.valeryvpetrov.decision.feature.decision.api.Component
import dev.valeryvpetrov.decision.feature.decision.api.DecisionComponent
import dev.valeryvpetrov.decision.feature.decision.api.DecisionComponentFactory
import dev.valeryvpetrov.decision.feature.decision.api.Intent
import dev.valeryvpetrov.decision.feature.decision.api.Label
import dev.valeryvpetrov.decision.feature.decision.api.State
import dev.valeryvpetrov.decision.feature.decision.impl.mvi.StoreFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

class RealComponent(
    componentContext: ComponentContext,
    private val onGoToSolutions: () -> Unit,
    private val onRestart: () -> Unit,
    storeFactoryProvider: Provider<StoreFactory>,
) : ComponentContext by componentContext, DecisionComponent {

    class Factory(
        private val storeFactoryProvider: Provider<StoreFactory>,
    ) : DecisionComponentFactory {

        override fun create(
            componentContext: ComponentContext,
            onGoToSolutions: () -> Unit,
            onRestart: () -> Unit
        ): Component = RealComponent(
            componentContext = componentContext,
            onGoToSolutions = onGoToSolutions,
            onRestart = onRestart,
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

    override fun onRestart() = onRestart.invoke()
}