package dev.valeryvpetrov.decision.feature.history.presentation.mvi

import dev.valeryvpetrov.decision.feature.history.api.model.History

sealed class Message {

    data class OnHistoryLoaded(val history: List<History>) : Message()
    data object OnNoHistory : Message()
}