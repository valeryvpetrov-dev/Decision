package decision.decision.mvi

sealed class Action {

    data object CalculateDecision : Action()
}