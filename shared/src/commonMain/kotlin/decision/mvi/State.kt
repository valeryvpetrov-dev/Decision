package decision.mvi

import kotlinx.serialization.Serializable
import model.MakeDecision

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