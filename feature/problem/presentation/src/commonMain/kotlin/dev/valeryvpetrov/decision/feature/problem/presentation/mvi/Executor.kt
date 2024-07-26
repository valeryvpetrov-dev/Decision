package dev.valeryvpetrov.decision.feature.problem.presentation.mvi

import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import dev.valeryvpetrov.decision.feature.problem.api.Problem

class Executor(
    private val onGoToSolutions: (Problem) -> Unit,
) : CoroutineExecutor<Intent, Nothing, State, Message, Nothing>() {

    interface Factory {

        fun create(onGoToSolutions: (Problem) -> Unit): Executor
    }

    override fun executeIntent(intent: Intent) {
        when (intent) {
            is Intent.ChangeProblemDescription -> dispatch(
                Message.OnChangeProblemDescription(intent.description)
            )

            Intent.GoToSolutions -> {
                val state = state()
                val problem = Problem(state.description)
                onGoToSolutions(problem)
            }

            is Intent.Restore -> dispatch(Message.OnRestore(problem = intent.problem))
        }
    }
}