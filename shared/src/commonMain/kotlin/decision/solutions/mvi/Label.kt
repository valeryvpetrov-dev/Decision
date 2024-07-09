package decision.solutions.mvi

sealed class Label {

    data object GoToProblem : Label()
    data object GoToDecision : Label()
}