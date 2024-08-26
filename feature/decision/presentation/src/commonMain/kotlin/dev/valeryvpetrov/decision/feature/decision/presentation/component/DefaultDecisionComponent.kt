package dev.valeryvpetrov.decision.feature.decision.presentation.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.backhandler.BackCallback
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.core.store.Store
import dev.valeryvpetrov.decision.base.api.Provider
import dev.valeryvpetrov.decision.feature.decision.presentation.mvi.DecisionIntent
import dev.valeryvpetrov.decision.feature.decision.presentation.mvi.DecisionState
import dev.valeryvpetrov.decision.feature.decision.presentation.mvi.StoreFactory

class DefaultDecisionComponent(
    componentContext: ComponentContext,
    private val decisionMessage: String,
    private val onGoToSolutions: () -> Unit,
    private val onRestart: () -> Unit,
    storeFactoryProvider: Provider<StoreFactory>,
) : ComponentContext by componentContext, DecisionComponent(
    componentContext = componentContext,
) {

    class Factory(
        private val storeFactoryProvider: Provider<StoreFactory>,
    ) : DecisionComponent.Factory {

        override fun create(
            componentContext: ComponentContext,
            decisionMessage: String,
            onGoToSolution: () -> Unit,
            onRestart: () -> Unit,
        ): DecisionComponent = DefaultDecisionComponent(
            componentContext = componentContext,
            decisionMessage = decisionMessage,
            onGoToSolutions = onGoToSolution,
            onRestart = onRestart,
            storeFactoryProvider = storeFactoryProvider,
        )
    }

    override val store: Store<DecisionIntent, DecisionState, Nothing> = instanceKeeper.getStore {
        storeFactoryProvider.get().create(
            stateKeeper = stateKeeper,
            decisionMessage = decisionMessage,
            onGoToSolutions = onGoToSolutions,
            onRestart = onRestart,
        )
    }

    private val backCallback = BackCallback {
        store.accept(DecisionIntent.Back)
    }

    init {
        backHandler.register(backCallback)
    }
}