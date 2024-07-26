package dev.valeryvpetrov.decision.feature.problem.presentation.component

import com.arkivanov.decompose.ComponentContext
import dev.valeryvpetrov.decision.base.presentation.BaseComponent
import dev.valeryvpetrov.decision.feature.problem.api.Problem
import dev.valeryvpetrov.decision.feature.problem.presentation.mvi.Intent
import dev.valeryvpetrov.decision.feature.problem.presentation.mvi.State

typealias ProblemComponent = Component
typealias ProblemComponentFactory = Component.Factory

interface Component : BaseComponent<State, Intent> {

    interface Factory {

        fun create(
            componentContext: ComponentContext,
            problem: Problem?,
            onGoToSolutions: (Problem) -> Unit,
        ): Component
    }
}