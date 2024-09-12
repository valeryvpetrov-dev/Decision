package dev.valeryvpetrov.decision.feature.make_decision.api

import dev.valeryvpetrov.decision.feature.problem.api.Problem
import dev.valeryvpetrov.decision.feature.solution.api.Solution

interface MakeDecisionUseCase {

    suspend fun clearDecision()

    suspend fun restore(makeDecision: MakeDecision.Builder)

    suspend fun setProblem(problem: Problem)
    suspend fun getProblem(): Problem?

    suspend fun setSolutions(solutions: List<Solution>)
    suspend fun getSolutions(): List<Solution>?

    suspend fun finalizeDecision(): String
}