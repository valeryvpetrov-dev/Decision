package dev.valeryvpetrov.decision.feature.problem.impl.mvi

import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import dev.valeryvpetrov.decision.data.api.DecisionRepository
import dev.valeryvpetrov.decision.domain.Problem
import dev.valeryvpetrov.decision.feature.problem.api.Intent
import dev.valeryvpetrov.decision.feature.problem.api.Label
import dev.valeryvpetrov.decision.feature.problem.api.State

class Executor(
    private val decisionRepository: DecisionRepository,
) : CoroutineExecutor<Intent, Nothing, State, Message, Label>() {

    override fun executeIntent(intent: Intent) {
        when (intent) {
            is Intent.ChangeProblemDescription -> dispatch(
                Message.OnChangeProblemDescription(intent.description)
            )

            Intent.GoToSolutions -> {
                val state = state()
                val problem = Problem(state.description)
                decisionRepository.setProblem(problem)
                publish(Label.GoToSolutions)
            }
        }
    }
}