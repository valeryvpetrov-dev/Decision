package dev.valeryvpetrov.decision.feature.make_decision.presentation.component

import kotlinx.serialization.Serializable
import dev.valeryvpetrov.decision.feature.problem.api.Problem as DomainProblem
import dev.valeryvpetrov.decision.feature.solution.api.Solution as DomainSolution

@Serializable
sealed class Config {

    @Serializable
    data class Problem(val problem: DomainProblem?) : Config() {
        override fun equals(other: Any?): Boolean {
            if (this !== other) return false
            if (this::class != other::class) return false
            return problem == other.problem
        }

        override fun hashCode(): Int {
            return problem?.hashCode() ?: 0
        }
    }

    @Serializable
    data class Solution(val solutions: List<DomainSolution>?) : Config()

    @Serializable
    data class Decision(val decisionMessage: String) : Config()
}