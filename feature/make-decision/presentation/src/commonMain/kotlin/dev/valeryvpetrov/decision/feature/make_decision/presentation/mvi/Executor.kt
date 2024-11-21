package dev.valeryvpetrov.decision.feature.make_decision.presentation.mvi

import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import dev.valeryvpetrov.decision.feature.make_decision.api.MakeDecisionUseCase
import dev.valeryvpetrov.decision.feature.problem.api.Problem
import dev.valeryvpetrov.decision.feature.solution.api.Solution
import kotlinx.coroutines.launch

class Executor(
    private val savedState: MakeDecisionState,
    private val makeDecisionUseCase: MakeDecisionUseCase,
    private val onGoToSolution: (List<Solution>?) -> Unit,
    private val onGoToDecision: (String) -> Unit,
    private val onBackToProblem: (Problem?) -> Unit,
    private val onRestart: (Problem?) -> Unit,
    private val onBackToSolution: (List<Solution>?) -> Unit,
) : CoroutineExecutor<MakeDecisionIntent, Action, MakeDecisionState, Message, Nothing>() {

    class Factory(
        private val makeDecisionUseCase: MakeDecisionUseCase,
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
            makeDecisionUseCase = makeDecisionUseCase,
            onGoToSolution = onGoToSolution,
            onGoToDecision = onGoToDecision,
            onBackToProblem = onBackToProblem,
            onRestart = onRestart,
            onBackToSolution = onBackToSolution,
        )
    }

    override fun executeIntent(intent: MakeDecisionIntent) {
        when (intent) {
            is MakeDecisionIntent.GoToSolution -> scope.launch {
                makeDecisionUseCase.setProblem(intent.problem)
                val solutions = makeDecisionUseCase.getSolutions()
                onGoToSolution(solutions)
            }

            is MakeDecisionIntent.GoToDecision -> scope.launch {
                makeDecisionUseCase.setSolutions(intent.solutions)
                val decisionMessage = makeDecisionUseCase.finalizeDecision()
                onGoToDecision(decisionMessage)
            }

            is MakeDecisionIntent.BackToProblem -> scope.launch {
                makeDecisionUseCase.setSolutions(intent.solutions)
                val problem = makeDecisionUseCase.getProblem()
                onBackToProblem(problem)
            }

            MakeDecisionIntent.Restart -> scope.launch {
                makeDecisionUseCase.clearDecision()
                val problem = makeDecisionUseCase.getProblem()
                onRestart(problem)
            }

            MakeDecisionIntent.BackToSolution -> scope.launch {
                val solutions = makeDecisionUseCase.getSolutions()
                onBackToSolution(solutions)
            }
        }
    }

    override fun executeAction(action: Action) {
        when (action) {
            Action.Restore -> scope.launch {
                val makeDecision = savedState.makeDecision
                makeDecisionUseCase.restore(makeDecision)
                dispatch(Message.OnRestore(makeDecision))
            }
        }
    }
}