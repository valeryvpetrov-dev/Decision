package mvi.decision

import model.Solution

sealed class Intent {
    data class ChangeProblemDescription(
        val description: String
    ) : Intent()

    data object AddNewSolution : Intent()

    data class ChangeSolutionDescription(
        val solution: Solution,
        val description: String
    ) : Intent()

    data class SelectSolution(
        val solution: Solution
    ) : Intent()

    data object MakeDecision : Intent()
    data object Clear : Intent()
}