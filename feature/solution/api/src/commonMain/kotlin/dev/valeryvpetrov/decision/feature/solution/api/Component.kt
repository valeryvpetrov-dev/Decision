package dev.valeryvpetrov.decision.feature.solution.api

import com.arkivanov.decompose.ComponentContext
import dev.valeryvpetrov.decision.base.api.BaseComponent

typealias SolutionComponent = Component
typealias SolutionComponentFactory = Component.Factory

interface Component : BaseComponent<State, Label, Intent> {

    fun onGoToProblem()
    fun onGoToDecision()

    interface Factory {

        fun create(
            componentContext: ComponentContext,
            onGoToProblem: () -> Unit,
            onGoToDecision: () -> Unit,
        ): Component
    }
}