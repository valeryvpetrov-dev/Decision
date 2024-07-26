package dev.valeryvpetrov.decision.feature.make_decision.impl.mvi

import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import dev.valeryvpetrov.decision.data.api.DecisionRepository
import dev.valeryvpetrov.decision.domain.MakeDecision
import dev.valeryvpetrov.decision.feature.make_decision.api.Label
import dev.valeryvpetrov.decision.feature.make_decision.api.State

class Executor(
    private val savedState: State,
    private val decisionRepository: DecisionRepository,
) : CoroutineExecutor<Intent, Action, State, Message, Label>() {

    class Factory(
        private val decisionRepository: DecisionRepository,
    ) {

        fun create(savedState: State): Executor = Executor(
            savedState = savedState,
            decisionRepository = decisionRepository,
        )
    }

    override fun executeIntent(intent: Intent) = when (intent) {
        is Intent.GoToSolution -> {
            decisionRepository.setProblem(intent.problem)
            val solutions = decisionRepository.getSolutions()
            publish(Label.GoToSolution(solutions))
        }

        is Intent.GoToDecision -> {
            decisionRepository.setSolutions(intent.solutions)
            val makeDecision = decisionRepository.getDecision()
            val decisionMessage = makeDecision.getDecisionMessage()
            publish(Label.GoToDecision(decisionMessage))
        }

        is Intent.BackToProblem -> {
            decisionRepository.setSolutions(intent.solutions)
            val problem = decisionRepository.getProblem()
            publish(Label.BackToProblem(problem))
        }

        Intent.Restart -> {
            decisionRepository.clearDecision()
            val problem = decisionRepository.getProblem()
            publish(Label.Restart(problem))
        }

        Intent.BackToSolution -> {
            val solutions = decisionRepository.getSolutions()
            publish(Label.BackToSolution(solutions))
        }
    }

    override fun executeAction(action: Action) {
        when (action) {
            Action.Restore -> {
                val makeDecision = savedState.makeDecision
                decisionRepository.restore(makeDecision)
                dispatch(Message.OnRestore(makeDecision))
            }
        }
    }

    private fun MakeDecision.getDecisionMessage(): String =
        "Problem ${problem.description} was solved by ${decision.description}"
}