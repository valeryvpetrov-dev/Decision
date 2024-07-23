package dev.valeryvpetrov.decision.data.impl

import dev.valeryvpetrov.decision.data.api.DecisionRepository
import dev.valeryvpetrov.decision.domain.MakeDecision
import dev.valeryvpetrov.decision.domain.Problem
import dev.valeryvpetrov.decision.domain.Solution

class DecisionRepositoryImpl : DecisionRepository {

    private var makeDecision = MakeDecision.Builder()

    override fun clearDecision() {
        makeDecision = MakeDecision.Builder()
    }

    override fun restore(makeDecision: MakeDecision.Builder) {
        this.makeDecision = makeDecision
    }

    override fun setProblem(problem: Problem) {
        makeDecision.problem(problem)
    }

    override fun getProblem(): Problem? = makeDecision.problem

    override fun setSolutions(solutions: List<Solution>) {
        makeDecision.solutions(solutions)
    }

    override fun getSolutions(): List<Solution>? = makeDecision.solutions

    override fun getDecision(): MakeDecision = makeDecision.build()
}