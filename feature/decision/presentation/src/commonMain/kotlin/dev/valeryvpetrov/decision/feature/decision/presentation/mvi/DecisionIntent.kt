package dev.valeryvpetrov.decision.feature.decision.presentation.mvi

sealed class DecisionIntent {

    data class Restore(val decisionMessage: String) : DecisionIntent()
    data object GoToSolutions : DecisionIntent()
    data object Restart : DecisionIntent()
    data object Back : DecisionIntent()
}