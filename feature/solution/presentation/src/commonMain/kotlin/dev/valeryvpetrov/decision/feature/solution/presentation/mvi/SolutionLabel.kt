package dev.valeryvpetrov.decision.feature.solution.presentation.mvi

sealed class SolutionLabel {

    data class OnAddNewSolutionFailure(val message: String) : SolutionLabel()
}