package decision.decision.mvi

sealed class Message {

    data class OnCalculateDecision(val decisionMessage: String) : Message()
    data object OnGoToSolutions : Message()
    data object OnRestart : Message()
}