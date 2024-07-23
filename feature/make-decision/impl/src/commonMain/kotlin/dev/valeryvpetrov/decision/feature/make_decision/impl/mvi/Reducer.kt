package dev.valeryvpetrov.decision.feature.make_decision.impl.mvi

import dev.valeryvpetrov.decision.feature.make_decision.api.State
import com.arkivanov.mvikotlin.core.store.Reducer as MviReducer

class Reducer : MviReducer<State, Message> {

    override fun State.reduce(msg: Message): State = when (msg) {
        is Message.OnRestore -> copy(makeDecision = msg.makeDecision)
    }
}
