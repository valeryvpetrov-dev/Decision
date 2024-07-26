package dev.valeryvpetrov.decision.feature.make_decision.presentation.mvi

sealed class Action {

    data object Restore : Action()
}