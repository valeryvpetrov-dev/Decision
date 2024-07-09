package decision.decision.mvi

sealed class Label {

    data object GoToSolutions : Label()
    data object Restart : Label()
}