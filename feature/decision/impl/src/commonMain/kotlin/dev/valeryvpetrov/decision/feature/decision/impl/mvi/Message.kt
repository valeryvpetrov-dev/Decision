package dev.valeryvpetrov.decision.feature.decision.impl.mvi

sealed class Message {

    data class OnRestore(val decisionMessage: String) : Message()
    data class OnCalculateDecision(val decisionMessage: String) : Message()
    data object OnGoToSolutions : Message()
    data object OnRestart : Message()
}