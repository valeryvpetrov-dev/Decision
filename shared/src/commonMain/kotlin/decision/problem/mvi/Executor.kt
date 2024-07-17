package decision.problem.mvi

import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import decision.repository.DecisionRepository
import model.Problem

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