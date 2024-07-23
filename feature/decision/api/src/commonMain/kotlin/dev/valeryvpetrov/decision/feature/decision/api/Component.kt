package dev.valeryvpetrov.decision.feature.decision.api

import com.arkivanov.decompose.ComponentContext
import dev.valeryvpetrov.decision.base.api.BaseComponent

typealias DecisionComponent = Component
typealias DecisionComponentFactory = Component.Factory

interface Component : BaseComponent<State, Label, Intent> {

    fun onGoToSolutions()
    fun onRestart()

    interface Factory {

        fun create(
            componentContext: ComponentContext,
            onGoToSolutions: () -> Unit,
            onRestart: () -> Unit,
        ): Component
    }
}