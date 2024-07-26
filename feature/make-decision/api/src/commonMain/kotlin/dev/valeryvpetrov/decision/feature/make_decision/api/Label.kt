package dev.valeryvpetrov.decision.feature.make_decision.api

import dev.valeryvpetrov.decision.domain.Problem
import dev.valeryvpetrov.decision.domain.Solution

sealed class Label {

    data class GoToSolution(val solutions: List<Solution>?) : Label()
    data class GoToDecision(val decisionMessage: String) : Label()
    data class BackToProblem(val problem: Problem?) : Label()
    data class BackToSolution(val solutions: List<Solution>?) : Label()
    data class Restart(val problem: Problem?) : Label()
}

