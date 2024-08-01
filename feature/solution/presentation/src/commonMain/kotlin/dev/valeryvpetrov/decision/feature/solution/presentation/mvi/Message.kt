package dev.valeryvpetrov.decision.feature.solution.presentation.mvi

import dev.valeryvpetrov.decision.feature.solution.api.Solution

sealed class Message {

    data class OnRestore(val solutions: List<Solution>?) : Message()

    data object OnAddNewSolution : Message()

    data class OnChangeSolutionDescription(
        val index: Int,
        val description: String
    ) : Message()

    data class OnSelectSolution(val index: Int) : Message()
    data class OnDeleteSolution(val index: Int) : Message()
}