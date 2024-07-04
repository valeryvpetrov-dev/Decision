package mvi.decision

import model.Problem
import model.Solution

sealed class State(
    open val problem: Problem,
) {

    companion object {

        fun initial(): State = NoSolutions(
            problem = Problem.empty(),
            isMakeDecisionEnabled = false,
        )
    }

    open val isMakeDecisionEnabled: Boolean
        get() = problem.description.isNotBlank()

    data class NoSolutions(
        override val problem: Problem,
        override val isMakeDecisionEnabled: Boolean,
    ) : State(problem)

    sealed class WithSolutions(
        override val problem: Problem,
        open val solutions: List<Solution>,
    ) : State(problem) {

        override val isMakeDecisionEnabled: Boolean
            get() = super.isMakeDecisionEnabled && solutions.any {
                it.isSelected && it.description.isNotBlank()
            }

        data class NoDecision(
            override val problem: Problem,
            override val solutions: List<Solution>,
        ) : WithSolutions(problem, solutions)

        data class WithDecision(
            override val problem: Problem,
            override val solutions: List<Solution>,
            val decisionMessage: String
        ) : WithSolutions(problem, solutions)
    }
}