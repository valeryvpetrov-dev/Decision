package dev.valeryvpetrov.decision.feature.problem.impl.mvi

import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import dev.valeryvpetrov.decision.domain.Problem
import dev.valeryvpetrov.decision.feature.problem.api.Intent
import dev.valeryvpetrov.decision.feature.problem.api.State

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