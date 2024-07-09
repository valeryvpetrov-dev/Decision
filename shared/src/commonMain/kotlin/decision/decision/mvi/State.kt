package decision.decision.mvi

data class State(
    val decision: String,
    val isGoToSolutionsEnabled: Boolean,
    val isRestartEnabled: Boolean,
) {

    companion object {

        fun initial(): State = State(
            decision = "",
            isGoToSolutionsEnabled = true,
            isRestartEnabled = true,
        )
    }
}