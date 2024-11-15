package dev.valeryvpetrov.decision.feature.problem.presentation.mvi

import kotlinx.serialization.Serializable

@Serializable
data class ProblemState(
    val problemTextFieldState: TextFieldState,
    val isGoToSolutionsEnabled: Boolean,
) {

    companion object {

        val STATE_KEEPER_KEY = "${this::class.qualifiedName}"

        fun initial(
            value: String = "",
            label: String = "",
            placeholder: String = "",
        ): ProblemState = ProblemState(
            problemTextFieldState = TextFieldState(
                value = value,
                label = label,
                placeholder = placeholder,
            ),
            isGoToSolutionsEnabled = false
        )
    }

    @Serializable
    data class TextFieldState(
        val value: String,
        val label: String,
        val placeholder: String,
    )
}