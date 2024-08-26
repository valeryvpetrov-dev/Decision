package dev.valeryvpetrov.decision.feature.problem.presentation.mvi

import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import dev.valeryvpetrov.decision.feature.problem.api.Problem

class Executor(
    private val onGoToSolutions: (Problem) -> Unit,
) : CoroutineExecutor<ProblemIntent, Nothing, ProblemState, Message, Nothing>() {

    interface Factory {

        fun create(onGoToSolutions: (Problem) -> Unit): Executor
    }

    override fun executeIntent(intent: ProblemIntent) {
        when (intent) {
            is ProblemIntent.ChangeProblemDescription -> dispatch(
                Message.OnChangeProblemDescription(intent.description)
            )

            ProblemIntent.GoToSolutions -> {
                val state = state()
                val problem = Problem(state.description)
                onGoToSolutions(problem)
            }

            is ProblemIntent.Restore -> dispatch(Message.OnRestore(problem = intent.problem))
        }
    }
}