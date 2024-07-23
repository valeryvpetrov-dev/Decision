package dev.valeryvpetrov.decision.feature.solution.impl.mvi

import dev.valeryvpetrov.decision.domain.Solution
import dev.valeryvpetrov.decision.feature.solution.api.State
import com.arkivanov.mvikotlin.core.store.Reducer as MviReducer

class Reducer : MviReducer<State, Message> {

    override fun State.reduce(msg: Message): State = when (msg) {
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

        is Message.OnRestoreState -> {
            val solutions = msg.solutions ?: emptyList()
            copy(
                solutions = solutions,
                isGoToDecisionEnabled = isGoToDecisionEnabled(solutions)
            )
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
