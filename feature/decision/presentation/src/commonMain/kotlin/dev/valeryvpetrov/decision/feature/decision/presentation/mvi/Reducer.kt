package dev.valeryvpetrov.decision.feature.decision.presentation.mvi

import com.arkivanov.mvikotlin.core.store.Reducer as MviReducer

class Reducer : MviReducer<DecisionState, Message> {

    override fun DecisionState.reduce(msg: Message): DecisionState = when (msg) {
        Message.OnGoToSolutions,
        is Message.OnRestart,
        -> this

        is Message.OnCalculateDecision -> copy(decisionMessage = msg.decisionMessage)
        is Message.OnRestore -> copy(decisionMessage = msg.decisionMessage)
    }
}
