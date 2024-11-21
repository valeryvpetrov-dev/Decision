package dev.valeryvpetrov.decision.feature.make_decision.impl

import dev.valeryvpetrov.decision.feature.make_decision.api.MakeDecision
import dev.valeryvpetrov.decision.feature.make_decision.api.MakeDecisionRepository
import dev.valeryvpetrov.decision.feature.problem.api.Problem
import dev.valeryvpetrov.decision.feature.solution.api.Solution

class MakeDecisionRepositoryImpl : MakeDecisionRepository {

    private var makeDecision = MakeDecision.Builder()

    override suspend fun clearDecision() {
        makeDecision = MakeDecision.Builder()
    }

    override suspend fun restore(makeDecision: MakeDecision.Builder) {
        this.makeDecision = makeDecision
    }

    override suspend fun setProblem(problem: Problem) {
        makeDecision.problem(problem)
    }

    override suspend fun getProblem(): Problem? = makeDecision.problem

    override suspend fun setSolutions(solutions: List<Solution>) {
        makeDecision.solutions(solutions)
    }

    override suspend fun getSolutions(): List<Solution>? = makeDecision.solutions

    override suspend fun finalizeDecision(): String {
        val decision = makeDecision.build()
        return "Problem \"${decision.problem.description}\" was solved by \"${decision.selectedSolution.description}\""
    }
}