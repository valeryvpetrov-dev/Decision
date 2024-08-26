package dev.valeryvpetrov.decision.feature.decision.presentation.mvi

import kotlinx.serialization.Serializable

@Serializable
data class DecisionState(
    val decisionMessage: String,
    val isGoToSolutionsEnabled: Boolean,
    val isRestartEnabled: Boolean,
) {

    companion object {

        val STATE_KEEPER_KEY = "${this::class.qualifiedName}"

        fun initial(): DecisionState = DecisionState(
            decisionMessage = "",
            isGoToSolutionsEnabled = true,
            isRestartEnabled = true,
        )
    }
}