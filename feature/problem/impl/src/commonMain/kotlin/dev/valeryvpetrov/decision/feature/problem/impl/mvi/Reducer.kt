package dev.valeryvpetrov.decision.feature.problem.impl.mvi

import dev.valeryvpetrov.decision.feature.problem.api.State
import com.arkivanov.mvikotlin.core.store.Reducer as MviReducer

class Reducer : MviReducer<State, Message> {

    override fun State.reduce(msg: Message): State = when (msg) {
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
