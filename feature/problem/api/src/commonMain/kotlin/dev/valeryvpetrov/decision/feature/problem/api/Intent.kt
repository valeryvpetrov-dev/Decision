package dev.valeryvpetrov.decision.feature.problem.api

sealed class Intent {
    data class ChangeProblemDescription(
        val description: String
    ) : Intent()

    data object GoToSolutions : Intent()
}