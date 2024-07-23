package dev.valeryvpetrov.decision.feature.solution.api

sealed class Label {

    data object GoToProblem : Label()
    data object GoToDecision : Label()
}