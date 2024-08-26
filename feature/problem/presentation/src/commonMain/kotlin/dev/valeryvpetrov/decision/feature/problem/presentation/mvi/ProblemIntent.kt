package dev.valeryvpetrov.decision.feature.problem.presentation.mvi

import dev.valeryvpetrov.decision.feature.problem.api.Problem

sealed class ProblemIntent {
    data class Restore(val problem: Problem?) : ProblemIntent()

    data class ChangeProblemDescription(
        val description: String,
    ) : ProblemIntent()

    data object GoToSolutions : ProblemIntent()
}