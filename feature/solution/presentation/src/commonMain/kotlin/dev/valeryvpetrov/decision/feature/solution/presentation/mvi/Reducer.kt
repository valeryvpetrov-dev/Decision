package dev.valeryvpetrov.decision.feature.solution.presentation.mvi

import dev.valeryvpetrov.decision.feature.solution.api.Solution
import com.arkivanov.mvikotlin.core.store.Reducer as MviReducer

class Reducer : MviReducer<SolutionState, Message> {

    override fun SolutionState.reduce(msg: Message): SolutionState = when (msg) {
        Message.OnAddNewSolution -> {
            val updatedSolutions = solutions + Solution.empty()
            copy(
                solutions = updatedSolutions,
                isGoToDecisionEnabled = isGoToDecisionEnabled(updatedSolutions)
            )
        }

        is Message.OnChangeSolutionDescription -> {
            val updatedSolutions = changeSolutionDescription(
                solutions = solutions,
                solutionToChangeIdx = msg.index,
                descriptionNew = msg.description
            )
            copy(
                solutions = updatedSolutions,
                isGoToDecisionEnabled = isGoToDecisionEnabled(updatedSolutions)
            )
        }

        is Message.OnSelectSolution -> {
            val updatedSolutions = changeSolutionsSelection(
                solutions = solutions,
                solutionToChangeIdx = msg.index,
            )
            copy(
                solutions = updatedSolutions,
                isGoToDecisionEnabled = isGoToDecisionEnabled(updatedSolutions)
            )
        }

        is Message.OnDeleteSolution -> {
            val updatedSolutions = solutions.toMutableList().apply {
                removeAt(msg.index)
            }
            copy(
                solutions = updatedSolutions,
                isGoToDecisionEnabled = isGoToDecisionEnabled(updatedSolutions)
            )
        }

        is Message.OnRestore -> {
            val solutions = msg.solutions ?: emptyList()
            copy(
                solutions = solutions,
                isGoToDecisionEnabled = isGoToDecisionEnabled(solutions)
            )
        }

        is Message.OnSuggestNewSolution.Loading -> {
            copy(isSuggestSolutionEnabled = false)
        }

        is Message.OnSuggestNewSolution.Success -> {
            val solutions = this.solutions + msg.solution
            copy(
                solutions = solutions,
                isSuggestSolutionEnabled = true,
                isGoToDecisionEnabled = isGoToDecisionEnabled(solutions)
            )
        }

        is Message.OnSuggestNewSolution.Failed -> {
            copy(isSuggestSolutionEnabled = true)
        }
    }

    private fun changeSolutionDescription(
        solutions: List<Solution>,
        solutionToChangeIdx: Int,
        descriptionNew: String,
    ): List<Solution> {
        return solutions.mapIndexed { index, solution ->
            if (index == solutionToChangeIdx) solution.copy(description = descriptionNew)
            else solution
        }
    }

    private fun changeSolutionsSelection(
        solutions: List<Solution>,
        solutionToChangeIdx: Int,
    ): List<Solution> {
        return solutions.mapIndexed { index, solution ->
            if (index == solutionToChangeIdx) {
                solution.copy(isSelected = true)
            } else {
                solution.copy(isSelected = false)
            }
        }
    }

    private fun isGoToDecisionEnabled(solutions: List<Solution>): Boolean {
        return solutions.all { it.description.isNotBlank() } &&
                solutions.count { it.isSelected } == 1
    }
}
