package dev.valeryvpetrov.decision.feature.history.presentation.mvi

import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import dev.valeryvpetrov.decision.feature.history.api.repository.HistoryRepository
import kotlinx.coroutines.launch

class Executor(
    private val historyRepository: HistoryRepository,
) : CoroutineExecutor<HistoryIntent, Action, HistoryState, Message, Nothing>() {

    override fun executeAction(action: Action) {
        when (action) {
            Action.LoadHistory -> scope.launch {
                val history = historyRepository.getAll()
                if (history.isEmpty()) dispatch(Message.OnNoHistory)
                else dispatch(Message.OnHistoryLoaded(history))
            }
        }
    }

    override fun executeIntent(intent: HistoryIntent) = Unit
}