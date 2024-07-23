package dev.valeryvpetrov.decision.feature.problem.api

import kotlinx.serialization.Serializable

@Serializable
data class State(
    val description: String,
    val isGoToSolutionsEnabled: Boolean,
) {

    companion object {

        val STATE_KEEPER_KEY = "${this::class.qualifiedName}"

        fun initial(): State = State(
            description = "",
            isGoToSolutionsEnabled = false
        )
    }
}