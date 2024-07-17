package decision.problem.mvi

sealed class Intent {
    data class ChangeProblemDescription(
        val description: String
    ) : Intent()

    data object GoToSolutions : Intent()
}