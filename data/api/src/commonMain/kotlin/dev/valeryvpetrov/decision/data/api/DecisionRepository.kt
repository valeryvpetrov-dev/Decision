package dev.valeryvpetrov.decision.data.api

import dev.valeryvpetrov.decision.domain.MakeDecision
import dev.valeryvpetrov.decision.domain.Problem
import dev.valeryvpetrov.decision.domain.Solution

interface DecisionRepository {

    fun clearDecision()

    fun restore(makeDecision: MakeDecision.Builder)

    fun setProblem(problem: Problem)
    fun getProblem(): Problem?

    fun setSolutions(solutions: List<Solution>)
    fun getSolutions(): List<Solution>?

    fun getDecision(): MakeDecision

}