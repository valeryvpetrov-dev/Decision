package dev.valeryvpetrov.decision.feature.solution.presentation.mvi

import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import dev.valeryvpetrov.decision.feature.solution.api.Solution

class Executor(
    private val onBackToProblem: (List<Solution>) -> Unit,
    private val onGoToDecision: (List<Solution>) -> Unit,
) : CoroutineExecutor<Intent, Nothing, State, Message, Nothing>() {

    interface Factory {
        fun create(
            onBackToProblem: (List<Solution>) -> Unit,
            onGoToDecision: (List<Solution>) -> Unit,
        ): Executor
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

            Intent.GoToProblem,
            Intent.Back,
            -> {
                val state = state()
                onBackToProblem(state.solutions)
            }

            Intent.GoToDecision -> {
                val state = state()
                onGoToDecision(state.solutions)
            }

            is Intent.Restore -> dispatch(Message.OnRestore(solutions = intent.solutions))
        }
    }
}