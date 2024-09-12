package dev.valeryvpetrov.decision.feature.history.impl.repository

import dev.valeryvpetrov.decision.feature.history.api.model.History
import dev.valeryvpetrov.decision.feature.history.api.repository.HistoryRepository

class HistoryRepositoryImpl : HistoryRepository {

    private val historyList: MutableList<History> = mutableListOf()

    override suspend fun save(history: History) {
        historyList.add(history)
    }

    override suspend fun getAll(): List<History> {
        return historyList
    }
}