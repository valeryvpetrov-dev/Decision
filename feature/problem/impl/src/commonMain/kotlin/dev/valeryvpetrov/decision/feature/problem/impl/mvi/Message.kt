package dev.valeryvpetrov.decision.feature.problem.impl.mvi

import dev.valeryvpetrov.decision.domain.Problem

sealed class Message {

    data class OnRestore(val problem: Problem?) : Message()

    data class OnChangeProblemDescription(val description: String) : Message()
}