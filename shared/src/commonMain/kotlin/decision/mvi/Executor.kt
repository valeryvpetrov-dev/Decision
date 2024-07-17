package decision.mvi

import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import decision.repository.DecisionRepository

class Executor(
    private val savedState: State,
    private val decisionRepository: DecisionRepository,
) : CoroutineExecutor<Nothing, Action, State, Message, Nothing>() {

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