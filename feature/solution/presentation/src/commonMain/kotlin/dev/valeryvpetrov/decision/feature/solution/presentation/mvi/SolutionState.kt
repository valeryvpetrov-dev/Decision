package dev.valeryvpetrov.decision.feature.solution.presentation.mvi

import dev.valeryvpetrov.decision.feature.solution.api.Solution
import kotlinx.serialization.Serializable

@Serializable
data class SolutionState(
    // TODO: use immutable list
    val solutions: List<Solution>,
    val isSuggestSolutionEnabled: Boolean,
    val isGoToDecisionEnabled: Boolean,
    val isGoToProblemEnabled: Boolean,
) {

    companion object {

        val STATE_KEEPER_KEY = "${this::class.qualifiedName}"

        fun initial(): SolutionState = SolutionState(
            solutions = emptyList(),
            isSuggestSolutionEnabled = true,
            isGoToDecisionEnabled = false,
            isGoToProblemEnabled = true,
        )
    }
}