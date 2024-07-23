package dev.valeryvpetrov.decision.feature.make_decision.impl.mvi

import dev.valeryvpetrov.decision.domain.MakeDecision

sealed class Message {

    data class OnRestore(val makeDecision: MakeDecision.Builder) : Message()
}