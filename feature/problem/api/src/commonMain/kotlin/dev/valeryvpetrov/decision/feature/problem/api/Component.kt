package dev.valeryvpetrov.decision.feature.problem.api

import com.arkivanov.decompose.ComponentContext
import dev.valeryvpetrov.decision.base.api.BaseComponent

typealias ProblemComponent = Component
typealias ProblemComponentFactory = Component.Factory

interface Component : BaseComponent<State, Label, Intent> {

    fun onGoToSolutions()

    interface Factory {

        fun create(
            componentContext: ComponentContext,
            onGoToSolutions: () -> Unit,
        ): Component
    }
}