package dev.valeryvpetrov.decision.feature.solution.api

import dev.valeryvpetrov.decision.domain.Solution
import kotlinx.serialization.Serializable

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