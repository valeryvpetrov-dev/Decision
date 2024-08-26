package dev.valeryvpetrov.decision.feature.problem.presentation.mvi

import com.arkivanov.mvikotlin.core.store.Reducer as MviReducer

class Reducer : MviReducer<ProblemState, Message> {

    override fun ProblemState.reduce(msg: Message): ProblemState = when (msg) {
        is Message.OnChangeProblemDescription -> {
            copy(
                description = msg.description,
                isGoToSolutionsEnabled = isGoToSolutionsEnabled(msg.description)
            )
        }

        is Message.OnRestore -> {
            val description = msg.problem?.description ?: ""
            copy(
                description = description,
                isGoToSolutionsEnabled = isGoToSolutionsEnabled(description)
            )
        }
    }

    private fun isGoToSolutionsEnabled(description: String): Boolean {
        return description.isNotBlank()
    }
}
