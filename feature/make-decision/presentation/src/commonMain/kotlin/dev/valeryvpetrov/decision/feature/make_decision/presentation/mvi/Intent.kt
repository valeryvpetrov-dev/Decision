package dev.valeryvpetrov.decision.feature.make_decision.presentation.mvi

import dev.valeryvpetrov.decision.feature.problem.api.Problem
import dev.valeryvpetrov.decision.feature.solution.api.Solution

sealed class Intent {

    data class GoToSolution(val problem: Problem) : Intent()
    data class GoToDecision(val solutions: List<Solution>) : Intent()
    data class BackToProblem(val solutions: List<Solution>) : Intent()
    data object BackToSolution : Intent()
    data object Restart : Intent()
}

