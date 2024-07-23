package dev.valeryvpetrov.decision.feature.decision.impl.mvi

sealed class Action {

    data object CalculateDecision : Action()
}