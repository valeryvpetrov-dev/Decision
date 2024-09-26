package dev.valeryvpetrov.decision.feature.history.presentation.mvi

import com.arkivanov.mvikotlin.core.store.Reducer as MviReducer

class Reducer : MviReducer<HistoryState, Message> {

    override fun HistoryState.reduce(msg: Message): HistoryState = when (msg) {
        is Message.OnHistoryLoaded -> HistoryState.Content(msg.history)
        is Message.OnNoHistory -> HistoryState.NoContent
    }
}