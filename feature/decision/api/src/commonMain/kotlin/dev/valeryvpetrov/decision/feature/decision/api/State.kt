package dev.valeryvpetrov.decision.feature.decision.api

import kotlinx.serialization.Serializable

@Serializable
data class State(
    val decisionMessage: String,
    val isGoToSolutionsEnabled: Boolean,
    val isRestartEnabled: Boolean,
) {

    companion object {

        val STATE_KEEPER_KEY = "${this::class.qualifiedName}"

        fun initial(): State = State(
            decisionMessage = "",
            isGoToSolutionsEnabled = true,
            isRestartEnabled = true,
        )
    }
}