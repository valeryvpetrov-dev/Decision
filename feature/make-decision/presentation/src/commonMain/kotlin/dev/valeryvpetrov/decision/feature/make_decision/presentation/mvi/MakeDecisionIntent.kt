package dev.valeryvpetrov.decision.feature.make_decision.presentation.mvi

import dev.valeryvpetrov.decision.feature.problem.api.Problem
import dev.valeryvpetrov.decision.feature.solution.api.Solution

sealed class MakeDecisionIntent {

    data class GoToSolution(val problem: Problem) : MakeDecisionIntent()
    data class GoToDecision(val solutions: List<Solution>) : MakeDecisionIntent()
    data class BackToProblem(val solutions: List<Solution>) : MakeDecisionIntent()
    data object BackToSolution : MakeDecisionIntent()
    data object Restart : MakeDecisionIntent()
}

