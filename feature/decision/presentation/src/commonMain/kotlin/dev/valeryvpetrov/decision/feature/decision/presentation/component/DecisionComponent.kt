package dev.valeryvpetrov.decision.feature.decision.presentation.component

import com.arkivanov.decompose.ComponentContext
import dev.valeryvpetrov.decision.base.presentation.ComponentWithStore
import dev.valeryvpetrov.decision.feature.decision.presentation.mvi.DecisionIntent
import dev.valeryvpetrov.decision.feature.decision.presentation.mvi.DecisionState

abstract class DecisionComponent(
    componentContext: ComponentContext,
) : ComponentWithStore<DecisionState, DecisionIntent, Nothing>(
    componentContext = componentContext,
) {

    interface Factory {

        fun create(
            componentContext: ComponentContext,
            decisionMessage: String,
            onGoToSolution: () -> Unit,
            onRestart: () -> Unit,
        ): DecisionComponent
    }
}