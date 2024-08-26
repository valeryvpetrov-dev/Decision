package dev.valeryvpetrov.decision.feature.solution.presentation.mvi

import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import dev.valeryvpetrov.decision.feature.solution.api.Solution
import dev.valeryvpetrov.decision.feature.solution.api.SuggestSolutionUseCase
import kotlinx.coroutines.launch

class Executor(
    private val onBackToProblem: (List<Solution>) -> Unit,
    private val onGoToDecision: (List<Solution>) -> Unit,
    private val suggestSolutionUseCase: SuggestSolutionUseCase,
) : CoroutineExecutor<SolutionIntent, Nothing, SolutionState, Message, SolutionLabel>() {

    interface Factory {
        fun create(
            onBackToProblem: (List<Solution>) -> Unit,
            onGoToDecision: (List<Solution>) -> Unit,
        ): Executor
    }

    override fun executeIntent(intent: SolutionIntent) {
        when (intent) {
            is SolutionIntent.AddNewSolution -> dispatch(Message.OnAddNewSolution)
            is SolutionIntent.ChangeSolutionDescription -> dispatch(
                Message.OnChangeSolutionDescription(
                    index = intent.index,
                    description = intent.description
                )
            )

            is SolutionIntent.SelectSolution -> dispatch(Message.OnSelectSolution(index = intent.index))
            is SolutionIntent.DeleteSolution -> dispatch(Message.OnDeleteSolution(index = intent.index))

            SolutionIntent.GoToProblem,
            SolutionIntent.Back,
            -> {
                val state = state()
                onBackToProblem(state.solutions)
            }

            SolutionIntent.GoToDecision -> {
                val state = state()
                onGoToDecision(state.solutions)
            }

            is SolutionIntent.Restore -> dispatch(Message.OnRestore(solutions = intent.solutions))
            SolutionIntent.SuggestNewSolution -> scope.launch {
                dispatch(Message.OnSuggestNewSolution.Loading)
                val solutions = state().solutions
                try {
                    val newSolution = suggestSolutionUseCase.suggestSolution(
                        currentSolutions = solutions
                    )
                    dispatch(Message.OnSuggestNewSolution.Success(newSolution))
                } catch (e: Throwable) {
                    publish(SolutionLabel.OnAddNewSolutionFailure(e.message ?: "Ошибка"))
                    dispatch(Message.OnSuggestNewSolution.Failed(e))
                }
            }
        }
    }
}