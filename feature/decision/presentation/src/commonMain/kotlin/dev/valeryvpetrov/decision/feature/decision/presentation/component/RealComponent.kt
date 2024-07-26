package dev.valeryvpetrov.decision.feature.decision.presentation.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.backhandler.BackCallback
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import dev.valeryvpetrov.decision.base.api.Provider
import dev.valeryvpetrov.decision.feature.decision.presentation.mvi.Intent
import dev.valeryvpetrov.decision.feature.decision.presentation.mvi.State
import dev.valeryvpetrov.decision.feature.decision.presentation.mvi.StoreFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow

class RealComponent(
    componentContext: ComponentContext,
    private val decisionMessage: String,
    private val onGoToSolutions: () -> Unit,
    private val onRestart: () -> Unit,
    storeFactoryProvider: Provider<StoreFactory>,
) : ComponentContext by componentContext, DecisionComponent {

    class Factory(
        private val storeFactoryProvider: Provider<StoreFactory>,
    ) : DecisionComponentFactory {

        override fun create(
            componentContext: ComponentContext,
            decisionMessage: String,
            onGoToSolution: () -> Unit,
            onRestart: () -> Unit,
        ): Component = RealComponent(
            componentContext = componentContext,
            decisionMessage = decisionMessage,
            onGoToSolutions = onGoToSolution,
            onRestart = onRestart,
            storeFactoryProvider = storeFactoryProvider,
        )
    }

    private val store: Store<Intent, State, Nothing> = instanceKeeper.getStore {
        storeFactoryProvider.get().create(
            stateKeeper = stateKeeper,
            decisionMessage = decisionMessage,
            onGoToSolutions = onGoToSolutions,
            onRestart = onRestart,
        )
    }

    private val backCallback = BackCallback {
        store.accept(Intent.Back)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override val state: StateFlow<State> = store.stateFlow

    override fun accept(intent: Intent) = store.accept(intent)

    init {
        backHandler.register(backCallback)
    }
}