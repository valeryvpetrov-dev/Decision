package dev.valeryvpetrov.decision.feature.solution.presentation.mvi

import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import dev.valeryvpetrov.decision.feature.solution.api.Solution
import dev.valeryvpetrov.decision.feature.solution.api.SolutionRepository
import dev.valeryvpetrov.decision.feature.solution.api.SuggestSolutionUseCase
import kotlinx.coroutines.launch

class Executor(
    private val onBackToProblem: (List<Solution>) -> Unit,
    private val onGoToDecision: (List<Solution>) -> Unit,
    private val suggestSolutionUseCase: SuggestSolutionUseCase,
    private val solutionRepository: SolutionRepository,
) : CoroutineExecutor<SolutionIntent, Nothing, SolutionState, Message, SolutionLabel>() {

    interface Factory {
        fun create(
            onBackToProblem: (List<Solution>) -> Unit,
            onGoToDecision: (List<Solution>) -> Unit,
        ): Executor
    }

    override fun executeIntent(intent: SolutionIntent) {
        when (intent) {
            is SolutionIntent.AddNewSolution -> scope.launch {
                solutionRepository.create()
                dispatch(Message.OnChangeSolutions(solutions = solutionRepository.get()))
            }

            is SolutionIntent.ChangeSolutionDescription -> scope.launch {
                solutionRepository.changeDescription(
                    id = intent.id,
                    newDescription = intent.description
                )
                dispatch(Message.OnChangeSolutions(solutions = solutionRepository.get()))
            }

            is SolutionIntent.SelectSolution -> scope.launch {
                solutionRepository.select(id = intent.id)
                dispatch(Message.OnChangeSolutions(solutions = solutionRepository.get()))
            }

            is SolutionIntent.DeleteSolution -> scope.launch {
                solutionRepository.delete(id = intent.id)
                dispatch(Message.OnChangeSolutions(solutions = solutionRepository.get()))
            }

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
                try {
                    suggestSolutionUseCase.suggestSolution()
                    dispatch(
                        Message.OnSuggestNewSolution.Success(solutions = solutionRepository.get())
                    )
                } catch (e: Throwable) {
                    publish(SolutionLabel.OnAddNewSolutionFailure(e.message ?: "Ошибка"))
                    dispatch(Message.OnSuggestNewSolution.Failed(e))
                }
            }
        }
    }
}