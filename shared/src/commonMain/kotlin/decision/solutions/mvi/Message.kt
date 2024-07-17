package decision.solutions.mvi

import model.Solution

sealed class Message {

    data class OnRestoreState(val solutions: List<Solution>?) : Message()

    data object OnAddNewSolution : Message()

    data class OnChangeSolutionDescription(
        val index: Int,
        val description: String
    ) : Message()

    data class OnSelectSolution(val index: Int) : Message()
    data class OnDeleteSolution(val index: Int) : Message()
}