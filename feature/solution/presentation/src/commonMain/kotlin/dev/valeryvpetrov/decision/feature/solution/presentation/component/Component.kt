package dev.valeryvpetrov.decision.feature.solution.presentation.component

import com.arkivanov.decompose.ComponentContext
import dev.valeryvpetrov.decision.base.presentation.ComponentWithStore
import dev.valeryvpetrov.decision.feature.solution.api.Solution
import dev.valeryvpetrov.decision.feature.solution.presentation.mvi.Intent
import dev.valeryvpetrov.decision.feature.solution.presentation.mvi.Label
import dev.valeryvpetrov.decision.feature.solution.presentation.mvi.State

typealias SolutionComponent = Component
typealias SolutionComponentFactory = Component.Factory

abstract class Component(
    componentContext: ComponentContext,
) : ComponentWithStore<State, Intent, Label>(
    componentContext = componentContext,
) {

    interface Factory {

        fun create(
            componentContext: ComponentContext,
            solutions: List<Solution>?,
            onBackToProblem: (List<Solution>) -> Unit,
            onGoToDecision: (List<Solution>) -> Unit,
        ): Component
    }
}