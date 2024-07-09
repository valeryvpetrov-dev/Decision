package decision.solutions.mvi

import model.Solution

data class State(
    val solutions: List<Solution>,
    val isGoToDecisionEnabled: Boolean,
    val isGoToProblemEnabled: Boolean,
) {

    companion object {

        fun initial(): State = State(
            solutions = emptyList(),
            isGoToDecisionEnabled = false,
            isGoToProblemEnabled = true,
        )
    }
}