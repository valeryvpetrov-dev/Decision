package decision.mvi

sealed class Action {

    data object RestoreState : Action()
}