package decision.solutions.mvi

import kotlinx.serialization.Serializable
import model.Solution

@Serializable
data class State(
    val solutions: List<Solution>,
    val isGoToDecisionEnabled: Boolean,
    val isGoToProblemEnabled: Boolean,
) {

    companion object {

        val STATE_KEEPER_KEY = "${this::class.qualifiedName}"

        fun initial(): State = State(
            solutions = emptyList(),
            isGoToDecisionEnabled = false,
            isGoToProblemEnabled = true,
        )
    }
}