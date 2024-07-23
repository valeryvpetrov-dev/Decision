package dev.valeryvpetrov.decision.domain

import kotlinx.serialization.Serializable

@Serializable
data class MakeDecision(
    val problem: Problem,
    val solutions: List<Solution>,
) {

    val decision: Solution
        get() = solutions.first { it.isSelected }


    @Serializable
    class Builder {
        var problem: Problem? = null
        var solutions: List<Solution>? = null

        fun problem(problem: Problem) = apply { this.problem = problem }

        fun solutions(solutions: List<Solution>) = apply { this.solutions = solutions }

        fun build(): MakeDecision {
            val problem = this.problem ?: error("Problem must be provided")
            val solutions = this.solutions ?: error("Solutions must be provided")
            if (solutions.isEmpty()) error("At least one solution must be provided")
            if (solutions.none { it.isSelected }) error("No selected solution in $solutions")
            if (solutions.count { it.isSelected } > 1)
                error("Several solutions can't be selected $solutions")
            return MakeDecision(problem, solutions)
        }
    }
}