package dev.valeryvpetrov.decision.feature.solution.impl.mvi

sealed class Action {

    data object RestoreState : Action()
}