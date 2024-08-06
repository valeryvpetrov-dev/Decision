package dev.valeryvpetrov.decision.feature.decision.presentation.component

import com.arkivanov.decompose.ComponentContext
import dev.valeryvpetrov.decision.base.presentation.ComponentWithStore
import dev.valeryvpetrov.decision.feature.decision.presentation.mvi.Intent
import dev.valeryvpetrov.decision.feature.decision.presentation.mvi.State

typealias DecisionComponent = Component
typealias DecisionComponentFactory = Component.Factory

abstract class Component(
    componentContext: ComponentContext,
) : ComponentWithStore<State, Intent, Nothing>(
    componentContext = componentContext,
) {

    interface Factory {

        fun create(
            componentContext: ComponentContext,
            decisionMessage: String,
            onGoToSolution: () -> Unit,
            onRestart: () -> Unit,
        ): Component
    }
}