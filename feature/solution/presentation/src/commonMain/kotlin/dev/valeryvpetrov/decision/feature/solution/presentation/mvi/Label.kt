package dev.valeryvpetrov.decision.feature.solution.presentation.mvi

sealed class Label {

    data class OnAddNewSolutionFailure(val message: String) : Label()
}