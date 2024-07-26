package dev.valeryvpetrov.decision.feature.decision.api

sealed class Intent {

    data class Restore(val decisionMessage: String) : Intent()
    data object GoToSolutions : Intent()
    data object Restart : Intent()
    data object Back : Intent()
}