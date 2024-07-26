package dev.valeryvpetrov.decision.feature.problem.api

import dev.valeryvpetrov.decision.domain.Problem

sealed class Intent {
    data class Restore(val problem: Problem?) : Intent()

    data class ChangeProblemDescription(
        val description: String,
    ) : Intent()

    data object GoToSolutions : Intent()
}