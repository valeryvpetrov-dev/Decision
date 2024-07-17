package decision.decision.mvi

import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import decision.repository.DecisionRepository
import model.MakeDecision

class Executor(
    private val decisionRepository: DecisionRepository,
) : CoroutineExecutor<Intent, Action, State, Message, Label>() {

    override fun executeIntent(intent: Intent) {
        when (intent) {
            is Intent.GoToSolutions -> publish(Label.GoToSolutions)
            is Intent.Restart -> {
                decisionRepository.restart()
                publish(Label.Restart)
            }
        }
    }

    override fun executeAction(action: Action) {
        when (action) {
            Action.CalculateDecision -> {
                val makeDecision = decisionRepository.getDecision()
                dispatch(Message.OnCalculateDecision(makeDecision.getDecisionMessage()))
            }
        }
    }

    private fun MakeDecision.getDecisionMessage(): String =
        "Problem ${problem.description} was solved by ${decision.description}"
}