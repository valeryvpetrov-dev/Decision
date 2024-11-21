package dev.valeryvpetrov.decision.feature.history.impl.repository

import dev.valeryvpetrov.decision.feature.history.api.model.History
import dev.valeryvpetrov.decision.feature.history.api.repository.HistoryRepository

class HistoryRepositoryImpl : HistoryRepository {

    private val historyList: MutableList<History> = mutableListOf(
        History(0, "Test 0"),
        History(1, "Test 1")
    )

    override suspend fun save(decision: String) {
        val history = History(
            id = historyList.size,
            decisionMessage = decision,
        )
        historyList.add(history)
    }

    override suspend fun getAll(): List<History> {
        return historyList
    }
}