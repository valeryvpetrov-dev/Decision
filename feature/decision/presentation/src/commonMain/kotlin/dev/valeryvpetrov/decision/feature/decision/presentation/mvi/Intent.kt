package dev.valeryvpetrov.decision.feature.decision.presentation.mvi

sealed class Intent {

    data class Restore(val decisionMessage: String) : Intent()
    data object GoToSolutions : Intent()
    data object Restart : Intent()
    data object Back : Intent()
}