package dev.valeryvpetrov.decision.feature.problem.presentation.mvi

import dev.valeryvpetrov.decision.feature.problem.api.Problem

sealed class Message {

    data class OnRestore(val problem: Problem?) : Message()

    data class OnChangeProblemDescription(val description: String) : Message()
}