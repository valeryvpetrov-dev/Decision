package dev.valeryvpetrov.decision.feature.solution.api

sealed class Intent {
    data object AddNewSolution : Intent()

    data class ChangeSolutionDescription(
        val index: Int,
        val description: String
    ) : Intent()

    data class SelectSolution(val index: Int) : Intent()

    data class DeleteSolution(val index: Int) : Intent()

    data object GoToProblem : Intent()

    data object GoToDecision : Intent()
}