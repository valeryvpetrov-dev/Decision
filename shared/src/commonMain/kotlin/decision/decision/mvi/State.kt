package decision.decision.mvi

import kotlinx.serialization.Serializable

@Serializable
data class State(
    val decision: String,
    val isGoToSolutionsEnabled: Boolean,
    val isRestartEnabled: Boolean,
) {

    companion object {

        val STATE_KEEPER_KEY = "${this::class.qualifiedName}"

        fun initial(): State = State(
            decision = "",
            isGoToSolutionsEnabled = true,
            isRestartEnabled = true,
        )
    }
}