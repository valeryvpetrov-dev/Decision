package dev.valeryvpetrov.decision.feature.problem.presentation.mvi

import dev.valeryvpetrov.decision.feature.problem.api.Problem

sealed class Intent {
    data class Restore(val problem: Problem?) : Intent()

    data class ChangeProblemDescription(
        val description: String,
    ) : Intent()

    data object GoToSolutions : Intent()
}