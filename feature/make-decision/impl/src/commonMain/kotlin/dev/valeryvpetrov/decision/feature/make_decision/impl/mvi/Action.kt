package dev.valeryvpetrov.decision.feature.make_decision.impl.mvi

sealed class Action {

    data object Restore : Action()
}