package dev.valeryvpetrov.decision.feature.solution.presentation.mvi

import dev.valeryvpetrov.decision.feature.solution.api.Solution

sealed class SolutionIntent {

    data class Restore(val solutions: List<Solution>?) : SolutionIntent()

    data object AddNewSolution : SolutionIntent()
    data object SuggestNewSolution : SolutionIntent()

    data class ChangeSolutionDescription(val id: Int, val description: String) : SolutionIntent()

    data class SelectSolution(val id: Int) : SolutionIntent()

    data class DeleteSolution(val id: Int) : SolutionIntent()

    data object GoToProblem : SolutionIntent()

    data object GoToDecision : SolutionIntent()

    data object Back : SolutionIntent()
}