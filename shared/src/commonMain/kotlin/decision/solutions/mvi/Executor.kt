package decision.solutions.mvi

import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import decision.repository.DecisionRepository

class Executor(
    private val decisionRepository: DecisionRepository,
) : CoroutineExecutor<Intent, Action, State, Message, Label>() {

    override fun executeAction(action: Action) = when (action) {
        Action.RestoreState -> {
            val solutions = decisionRepository.getSolutions()
            dispatch(Message.OnRestoreState(solutions))
        }
    }

    override fun executeIntent(intent: Intent) {
        when (intent) {
            is Intent.AddNewSolution -> dispatch(Message.OnAddNewSolution)
            is Intent.ChangeSolutionDescription -> dispatch(
                Message.OnChangeSolutionDescription(
                    index = intent.index,
                    description = intent.description
                )
            )

            is Intent.SelectSolution -> dispatch(Message.OnSelectSolution(index = intent.index))
            is Intent.DeleteSolution -> dispatch(Message.OnDeleteSolution(index = intent.index))

            Intent.GoToProblem -> {
                val state = state()
                decisionRepository.setSolutions(solutions = state.solutions)
                publish(Label.GoToProblem)
            }

            Intent.GoToDecision -> {
                val state = state()
                decisionRepository.setSolutions(solutions = state.solutions)
                publish(Label.GoToDecision)
            }
        }
    }
}