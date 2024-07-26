package dev.valeryvpetrov.decision.feature.decision.presentation.mvi

import com.arkivanov.mvikotlin.core.store.Reducer as MviReducer

class Reducer : MviReducer<State, Message> {

    override fun State.reduce(msg: Message): State = when (msg) {
        Message.OnGoToSolutions,
        is Message.OnRestart,
        -> this

        is Message.OnCalculateDecision -> copy(decisionMessage = msg.decisionMessage)
        is Message.OnRestore -> copy(decisionMessage = msg.decisionMessage)
    }
}
