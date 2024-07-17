package decision.solutions.mvi

sealed class Action {

    data object RestoreState : Action()
}