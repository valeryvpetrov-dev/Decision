package dev.valeryvpetrov.decision.feature.solution.api

import com.arkivanov.decompose.ComponentContext
import dev.valeryvpetrov.decision.base.api.BaseComponent
import dev.valeryvpetrov.decision.domain.Solution

typealias SolutionComponent = Component
typealias SolutionComponentFactory = Component.Factory

interface Component : BaseComponent<State, Intent> {

    interface Factory {

        fun create(
            componentContext: ComponentContext,
            solutions: List<Solution>?,
            onBackToProblem: (List<Solution>) -> Unit,
            onGoToDecision: (List<Solution>) -> Unit,
        ): Component
    }
}