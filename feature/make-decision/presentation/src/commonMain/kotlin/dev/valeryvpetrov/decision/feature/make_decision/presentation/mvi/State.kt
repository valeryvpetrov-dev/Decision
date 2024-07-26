package dev.valeryvpetrov.decision.feature.make_decision.presentation.mvi

import dev.valeryvpetrov.decision.feature.make_decision.api.MakeDecision
import kotlinx.serialization.Serializable

@Serializable
data class State(
    val makeDecision: MakeDecision.Builder,
) {

    companion object {

        val STATE_KEEPER_KEY = "${this::class.qualifiedName}"

        fun initial(): State = State(
            makeDecision = MakeDecision.Builder(),
        )
    }
}