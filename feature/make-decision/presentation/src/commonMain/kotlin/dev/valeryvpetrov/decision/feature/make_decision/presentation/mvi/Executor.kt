package dev.valeryvpetrov.decision.feature.make_decision.presentation.mvi

import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import dev.valeryvpetrov.decision.feature.make_decision.api.MakeDecision
import dev.valeryvpetrov.decision.feature.make_decision.api.MakeDecisionRepository
import dev.valeryvpetrov.decision.feature.problem.api.Problem
import dev.valeryvpetrov.decision.feature.solution.api.Solution

class Executor(
    private val savedState: MakeDecisionState,
    private val makeDecisionRepository: MakeDecisionRepository,
    private val onGoToSolution: (List<Solution>?) -> Unit,
    private val onGoToDecision: (String) -> Unit,
    private val onBackToProblem: (Problem?) -> Unit,
    private val onRestart: (Problem?) -> Unit,
    private val onBackToSolution: (List<Solution>?) -> Unit,
) : CoroutineExecutor<MakeDecisionIntent, Action, MakeDecisionState, Message, Nothing>() {

    class Factory(
        private val makeDecisionRepository: MakeDecisionRepository,
    ) {

        fun create(
            savedState: MakeDecisionState,
            onGoToSolution: (List<Solution>?) -> Unit,
            onGoToDecision: (String) -> Unit,
            onBackToProblem: (Problem?) -> Unit,
            onRestart: (Problem?) -> Unit,
            onBackToSolution: (List<Solution>?) -> Unit,
        ): Executor = Executor(
            savedState = savedState,
            makeDecisionRepository = makeDecisionRepository,
            onGoToSolution = onGoToSolution,
            onGoToDecision = onGoToDecision,
            onBackToProblem = onBackToProblem,
            onRestart = onRestart,
            onBackToSolution = onBackToSolution,
        )
    }

    override fun executeIntent(intent: MakeDecisionIntent) = when (intent) {
        is MakeDecisionIntent.GoToSolution -> {
            makeDecisionRepository.setProblem(intent.problem)
            val solutions = makeDecisionRepository.getSolutions()
            onGoToSolution(solutions)
        }

        is MakeDecisionIntent.GoToDecision -> {
            makeDecisionRepository.setSolutions(intent.solutions)
            val makeDecision = makeDecisionRepository.getDecision()
            val decisionMessage = makeDecision.getDecisionMessage()
            onGoToDecision(decisionMessage)
        }

        is MakeDecisionIntent.BackToProblem -> {
            makeDecisionRepository.setSolutions(intent.solutions)
            val problem = makeDecisionRepository.getProblem()
            onBackToProblem(problem)
        }

        MakeDecisionIntent.Restart -> {
            makeDecisionRepository.clearDecision()
            val problem = makeDecisionRepository.getProblem()
            onRestart(problem)
        }

        MakeDecisionIntent.BackToSolution -> {
            val solutions = makeDecisionRepository.getSolutions()
            onBackToSolution(solutions)
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