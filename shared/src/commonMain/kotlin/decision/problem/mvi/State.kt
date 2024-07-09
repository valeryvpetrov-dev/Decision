package decision.problem.mvi

data class State(
    val description: String,
    val isGoToSolutionsEnabled: Boolean,
) {

    companion object {

        fun initial(): State = State(
            description = "",
            isGoToSolutionsEnabled = false
        )
    }
}