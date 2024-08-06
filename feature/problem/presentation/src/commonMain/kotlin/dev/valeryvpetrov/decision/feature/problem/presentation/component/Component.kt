package dev.valeryvpetrov.decision.feature.problem.presentation.component

import com.arkivanov.decompose.ComponentContext
import dev.valeryvpetrov.decision.base.presentation.ComponentWithStore
import dev.valeryvpetrov.decision.feature.problem.api.Problem
import dev.valeryvpetrov.decision.feature.problem.presentation.mvi.Intent
import dev.valeryvpetrov.decision.feature.problem.presentation.mvi.State

typealias ProblemComponent = Component
typealias ProblemComponentFactory = Component.Factory

abstract class Component(
    componentContext: ComponentContext,
) : ComponentWithStore<State, Intent, Nothing>(
    componentContext = componentContext,
) {

    interface Factory {

        fun create(
            componentContext: ComponentContext,
            problem: Problem?,
            onGoToSolutions: (Problem) -> Unit,
        ): Component
    }
}