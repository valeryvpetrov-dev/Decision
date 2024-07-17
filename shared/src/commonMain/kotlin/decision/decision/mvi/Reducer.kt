package decision.decision.mvi

import com.arkivanov.mvikotlin.core.store.Reducer as MviReducer

class Reducer : MviReducer<State, Message> {

    override fun State.reduce(msg: Message): State = when (msg) {
        Message.OnGoToSolutions,
        is Message.OnRestart -> this

        is Message.OnCalculateDecision -> copy(decision = msg.decisionMessage)
    }
}
