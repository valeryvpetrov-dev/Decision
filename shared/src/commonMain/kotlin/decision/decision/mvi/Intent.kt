package decision.decision.mvi

sealed class Intent {

    data object Refresh : Intent()
    data object GoToSolutions : Intent()
    data object Restart : Intent()
}