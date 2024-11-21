package dev.valeryvpetrov.decision.feature.history.presentation.mvi

sealed class Action {

    data object LoadHistory : Action()
}