package dev.valeryvpetrov.decision.feature.solution.presentation.mvi

import dev.valeryvpetrov.decision.feature.solution.api.Solution

sealed class Message {

    data class OnRestore(val solutions: List<Solution>?) : Message()

    sealed class OnSuggestNewSolution : Message() {
        data object Loading : Message()
        data class Success(val solutions: List<Solution>) : Message()
        data class Failed(val error: Throwable) : Message()
    }

    data class OnChangeSolutions(val solutions: List<Solution>) : Message()
}