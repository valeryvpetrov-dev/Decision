package dev.valeryvpetrov.decision.feature.make_decision.impl.mvi

import dev.valeryvpetrov.decision.domain.Problem
import dev.valeryvpetrov.decision.domain.Solution

sealed class Intent {

    data class GoToSolution(val problem: Problem) : Intent()
    data class GoToDecision(val solutions: List<Solution>) : Intent()
    data class BackToProblem(val solutions: List<Solution>) : Intent()
    data object BackToSolution : Intent()
    data object Restart : Intent()
}

