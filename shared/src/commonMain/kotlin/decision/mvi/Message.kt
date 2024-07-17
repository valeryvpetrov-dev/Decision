package decision.mvi

import model.MakeDecision

sealed class Message {

    data class OnRestore(val makeDecision: MakeDecision.Builder) : Message()
}