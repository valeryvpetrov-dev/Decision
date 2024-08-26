package dev.valeryvpetrov.decision.feature.problem.presentation.mvi

import kotlinx.serialization.Serializable

@Serializable
data class ProblemState(
    val description: String,
    val isGoToSolutionsEnabled: Boolean,
) {

    companion object {

        val STATE_KEEPER_KEY = "${this::class.qualifiedName}"

        fun initial(): ProblemState = ProblemState(
            description = "",
            isGoToSolutionsEnabled = false
        )
    }
}