package dev.valeryvpetrov.decision.feature.make_decision.data

import dev.valeryvpetrov.decision.feature.make_decision.api.DecisionRepository
import dev.valeryvpetrov.decision.feature.make_decision.api.MakeDecision
import dev.valeryvpetrov.decision.feature.problem.api.Problem
import dev.valeryvpetrov.decision.feature.solution.api.Solution

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