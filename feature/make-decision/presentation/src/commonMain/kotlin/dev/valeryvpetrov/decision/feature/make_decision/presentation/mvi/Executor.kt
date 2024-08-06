package dev.valeryvpetrov.decision.feature.make_decision.presentation.mvi

import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import dev.valeryvpetrov.decision.feature.make_decision.api.MakeDecision
import dev.valeryvpetrov.decision.feature.make_decision.api.MakeDecisionRepository

class Executor(
    private val savedState: State,
    private val makeDecisionRepository: MakeDecisionRepository,
) : CoroutineExecutor<Intent, Action, State, Message, Label>() {

    class Factory(
        private val makeDecisionRepository: MakeDecisionRepository,
    ) {

        fun create(savedState: State): Executor = Executor(
            savedState = savedState,
            makeDecisionRepository = makeDecisionRepository,
        )
    }

    override fun executeIntent(intent: Intent) = when (intent) {
        is Intent.GoToSolution -> {
            makeDecisionRepository.setProblem(intent.problem)
            val solutions = makeDecisionRepository.getSolutions()
            publish(Label.GoToSolution(solutions))
        }

        is Intent.GoToDecision -> {
            makeDecisionRepository.setSolutions(intent.solutions)
            val makeDecision = makeDecisionRepository.getDecision()
            val decisionMessage = makeDecision.getDecisionMessage()
            publish(Label.GoToDecision(decisionMessage))
        }

        is Intent.BackToProblem -> {
            makeDecisionRepository.setSolutions(intent.solutions)
            val problem = makeDecisionRepository.getProblem()
            publish(Label.BackToProblem(problem))
        }

        Intent.Restart -> {
            makeDecisionRepository.clearDecision()
            val problem = makeDecisionRepository.getProblem()
            publish(Label.Restart(problem))
        }

        Intent.BackToSolution -> {
            val solutions = makeDecisionRepository.getSolutions()
            publish(Label.BackToSolution(solutions))
        }
    }

    override fun executeAction(action: Action) {
        when (action) {
            Action.Restore -> {
                val makeDecision = savedState.makeDecision
                makeDecisionRepository.restore(makeDecision)
                dispatch(Message.OnRestore(makeDecision))
            }
        }
    }

    private fun MakeDecision.getDecisionMessage(): String =
        "Problem ${problem.description} was solved by ${decision.description}"
}