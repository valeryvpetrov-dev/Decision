package decision.repository

import model.MakeDecision
import model.Problem
import model.Solution

interface DecisionRepository {

    fun clearDecision()

    fun restore(makeDecision: MakeDecision.Builder)

    fun setProblem(problem: Problem)
    fun getProblem(): Problem?

    fun setSolutions(solutions: List<Solution>)
    fun getSolutions(): List<Solution>?

    fun getDecision(): MakeDecision

}