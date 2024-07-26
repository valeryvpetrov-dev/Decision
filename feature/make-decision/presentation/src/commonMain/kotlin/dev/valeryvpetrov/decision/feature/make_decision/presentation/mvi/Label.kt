package dev.valeryvpetrov.decision.feature.make_decision.presentation.mvi

import dev.valeryvpetrov.decision.feature.problem.api.Problem
import dev.valeryvpetrov.decision.feature.solution.api.Solution

sealed class Label {

    data class GoToSolution(val solutions: List<Solution>?) : Label()
    data class GoToDecision(val decisionMessage: String) : Label()
    data class BackToProblem(val problem: Problem?) : Label()
    data class BackToSolution(val solutions: List<Solution>?) : Label()
    data class Restart(val problem: Problem?) : Label()
}

