package dev.valeryvpetrov.decision.feature.decision.impl.mvi

import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import dev.valeryvpetrov.decision.data.api.DecisionRepository
import dev.valeryvpetrov.decision.domain.MakeDecision
import dev.valeryvpetrov.decision.feature.decision.api.Intent
import dev.valeryvpetrov.decision.feature.decision.api.Label
import dev.valeryvpetrov.decision.feature.decision.api.State

class Executor(
    private val decisionRepository: DecisionRepository,
) : CoroutineExecutor<Intent, Action, State, Message, Label>() {

    override fun executeIntent(intent: Intent) {
        when (intent) {
            is Intent.GoToSolutions -> publish(Label.GoToSolutions)
            is Intent.Restart -> {
                decisionRepository.clearDecision()
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