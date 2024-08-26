package dev.valeryvpetrov.decision.feature.make_decision.presentation.mvi

import com.arkivanov.mvikotlin.core.store.Reducer as MviReducer

class Reducer : MviReducer<MakeDecisionState, Message> {

    override fun MakeDecisionState.reduce(msg: Message): MakeDecisionState = when (msg) {
        is Message.OnRestore -> copy(makeDecision = msg.makeDecision)
    }
}
