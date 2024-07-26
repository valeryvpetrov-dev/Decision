package dev.valeryvpetrov.decision.feature.solution.presentation.mvi

import dev.valeryvpetrov.decision.feature.solution.api.Solution

sealed class Intent {

    data class Restore(val solutions: List<Solution>?) : Intent()

    data object AddNewSolution : Intent()

    data class ChangeSolutionDescription(
        val index: Int,
        val description: String,
    ) : Intent()

    data class SelectSolution(val index: Int) : Intent()

    data class DeleteSolution(val index: Int) : Intent()

    data object GoToProblem : Intent()

    data object GoToDecision : Intent()

    data object Back : Intent()
}