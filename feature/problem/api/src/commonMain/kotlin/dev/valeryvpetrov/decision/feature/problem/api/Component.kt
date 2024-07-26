package dev.valeryvpetrov.decision.feature.problem.api

import com.arkivanov.decompose.ComponentContext
import dev.valeryvpetrov.decision.base.api.BaseComponent
import dev.valeryvpetrov.decision.domain.Problem

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