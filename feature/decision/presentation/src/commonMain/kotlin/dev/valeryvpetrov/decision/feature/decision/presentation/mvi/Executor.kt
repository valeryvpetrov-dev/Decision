package dev.valeryvpetrov.decision.feature.decision.presentation.mvi

import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor

class Executor(
    private val onGoToSolutions: () -> Unit,
    private val onRestart: () -> Unit,
) : CoroutineExecutor<Intent, Nothing, State, Message, Nothing>() {

    interface Factory {

        fun create(
            onGoToSolutions: () -> Unit,
            onRestart: () -> Unit,
        ): Executor
    }

    override fun executeIntent(intent: Intent) {
        when (intent) {
            is Intent.GoToSolutions,
            Intent.Back,
            -> onGoToSolutions()

            is Intent.Restart -> onRestart()
            is Intent.Restore -> dispatch(Message.OnRestore(decisionMessage = intent.decisionMessage))
        }
    }
}