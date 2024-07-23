package dev.valeryvpetrov.decision.feature.make_decision.impl.mvi

import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import dev.valeryvpetrov.decision.data.api.DecisionRepository
import dev.valeryvpetrov.decision.feature.make_decision.api.State

class Executor(
    private val savedState: State,
    private val decisionRepository: DecisionRepository,
) : CoroutineExecutor<Nothing, Action, State, Message, Nothing>() {

    class Factory(
        private val decisionRepository: DecisionRepository,
    ) {

        fun create(savedState: State): Executor = Executor(
            savedState = savedState,
            decisionRepository = decisionRepository,
        )
    }

    override fun executeAction(action: Action) {
        when (action) {
            Action.RestoreState -> {
                val makeDecision = savedState.makeDecision
                decisionRepository.restore(makeDecision)
                dispatch(Message.OnRestore(makeDecision))
            }
        }
    }
}