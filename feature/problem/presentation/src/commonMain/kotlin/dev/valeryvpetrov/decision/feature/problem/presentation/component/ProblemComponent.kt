package dev.valeryvpetrov.decision.feature.problem.presentation.component

import com.arkivanov.decompose.ComponentContext
import dev.valeryvpetrov.decision.base.presentation.ComponentWithStore
import dev.valeryvpetrov.decision.feature.problem.api.Problem
import dev.valeryvpetrov.decision.feature.problem.presentation.mvi.ProblemIntent
import dev.valeryvpetrov.decision.feature.problem.presentation.mvi.ProblemState

abstract class ProblemComponent(
    componentContext: ComponentContext,
) : ComponentWithStore<ProblemState, ProblemIntent, Nothing>(
    componentContext = componentContext,
) {

    interface Factory {

        fun create(
            componentContext: ComponentContext,
            problem: Problem?,
            onGoToSolutions: (Problem) -> Unit,
        ): ProblemComponent
    }
}