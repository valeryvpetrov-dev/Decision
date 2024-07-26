package dev.valeryvpetrov.decision.feature.make_decision.api

import dev.valeryvpetrov.decision.feature.problem.api.Problem
import dev.valeryvpetrov.decision.feature.solution.api.Solution

interface DecisionRepository {

    fun clearDecision()

    fun restore(makeDecision: MakeDecision.Builder)

    fun setProblem(problem: Problem)
    fun getProblem(): Problem?

    fun setSolutions(solutions: List<Solution>)
    fun getSolutions(): List<Solution>?

    fun getDecision(): MakeDecision

}