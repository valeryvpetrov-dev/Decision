package dev.valeryvpetrov.decision.feature.history.presentation.mvi

import dev.valeryvpetrov.decision.feature.history.api.model.History
import kotlinx.serialization.Serializable

@Serializable
sealed class HistoryState {

    @Serializable
    data class Content(
        val historyList: List<History>,
    ) : HistoryState()

    @Serializable
    data object NoContent : HistoryState()

    @Serializable
    data object Loading : HistoryState()

    @Serializable
    data object Error : HistoryState()

    companion object {

        val STATE_KEEPER_KEY = "${this::class.qualifiedName}"

        fun initial(): HistoryState = Loading
    }
}
