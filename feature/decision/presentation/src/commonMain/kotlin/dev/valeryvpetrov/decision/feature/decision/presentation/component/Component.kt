package dev.valeryvpetrov.decision.feature.decision.presentation.component

import com.arkivanov.decompose.ComponentContext
import dev.valeryvpetrov.decision.base.presentation.BaseComponent
import dev.valeryvpetrov.decision.feature.decision.presentation.mvi.Intent
import dev.valeryvpetrov.decision.feature.decision.presentation.mvi.State

typealias DecisionComponent = Component
typealias DecisionComponentFactory = Component.Factory

interface Component : BaseComponent<State, Intent> {

    interface Factory {

        fun create(
            componentContext: ComponentContext,
            decisionMessage: String,
            onGoToSolution: () -> Unit,
            onRestart: () -> Unit,
        ): Component
    }
}