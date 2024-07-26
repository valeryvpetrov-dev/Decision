package dev.valeryvpetrov.decision.feature.make_decision.presentation.mvi

import dev.valeryvpetrov.decision.feature.make_decision.api.MakeDecision

sealed class Message {

    data class OnRestore(val makeDecision: MakeDecision.Builder) : Message()
}