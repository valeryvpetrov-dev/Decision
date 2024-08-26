package dev.valeryvpetrov.decision.feature.decision.presentation.mvi

import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor

class Executor(
    private val onGoToSolutions: () -> Unit,
    private val onRestart: () -> Unit,
) : CoroutineExecutor<DecisionIntent, Nothing, DecisionState, Message, Nothing>() {

    interface Factory {

        fun create(
            onGoToSolutions: () -> Unit,
            onRestart: () -> Unit,
        ): Executor
    }

    override fun executeIntent(intent: DecisionIntent) {
        when (intent) {
            is DecisionIntent.GoToSolutions,
            DecisionIntent.Back,
            -> onGoToSolutions()

            is DecisionIntent.Restart -> onRestart()
            is DecisionIntent.Restore -> dispatch(Message.OnRestore(decisionMessage = intent.decisionMessage))
        }
    }
}