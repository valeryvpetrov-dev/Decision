package dev.valeryvpetrov.decision.feature.solution.presentation.component

import com.arkivanov.decompose.ComponentContext
import dev.valeryvpetrov.decision.base.presentation.ComponentWithStore
import dev.valeryvpetrov.decision.feature.solution.api.Solution
import dev.valeryvpetrov.decision.feature.solution.presentation.mvi.SolutionIntent
import dev.valeryvpetrov.decision.feature.solution.presentation.mvi.SolutionLabel
import dev.valeryvpetrov.decision.feature.solution.presentation.mvi.SolutionState

abstract class SolutionComponent(
    componentContext: ComponentContext,
) : ComponentWithStore<SolutionState, SolutionIntent, SolutionLabel>(
    componentContext = componentContext,
) {

    interface Factory {

        fun create(
            componentContext: ComponentContext,
            solutions: List<Solution>?,
            onBackToProblem: (List<Solution>) -> Unit,
            onGoToDecision: (List<Solution>) -> Unit,
        ): SolutionComponent
    }
}