package dev.valeryvpetrov.decision.feature.solution.presentation.mvi

import dev.valeryvpetrov.decision.feature.solution.api.Solution
import com.arkivanov.mvikotlin.core.store.Reducer as MviReducer

class Reducer : MviReducer<SolutionState, Message> {

    override fun SolutionState.reduce(msg: Message): SolutionState = when (msg) {
        is Message.OnChangeSolutions -> {
            val solutions = msg.solutions.toList()
            copy(
                solutions = solutions,
                isGoToDecisionEnabled = isGoToDecisionEnabled(solutions)
            )
        }
        is Message.OnRestore -> {
            val solutions = msg.solutions?.toList() ?: emptyList()
            copy(
                solutions = solutions,
                isGoToDecisionEnabled = isGoToDecisionEnabled(solutions)
            )
        }

        is Message.OnSuggestNewSolution.Loading -> {
            copy(isSuggestSolutionEnabled = false)
        }

        is Message.OnSuggestNewSolution.Success -> {
            val solutions = msg.solutions.toList()
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

    private fun isGoToDecisionEnabled(solutions: List<Solution>): Boolean {
        return solutions.all { it.description.isNotBlank() } &&
                solutions.count { it.isSelected } == 1
    }
}
