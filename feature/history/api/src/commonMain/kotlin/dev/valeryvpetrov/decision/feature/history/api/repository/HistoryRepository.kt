package dev.valeryvpetrov.decision.feature.history.api.repository

import dev.valeryvpetrov.decision.feature.history.api.model.History

interface HistoryRepository {

    suspend fun save(decision: String)

    suspend fun getAll(): List<History>
}