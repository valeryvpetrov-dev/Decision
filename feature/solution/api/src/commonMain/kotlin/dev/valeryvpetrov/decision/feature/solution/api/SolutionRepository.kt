package dev.valeryvpetrov.decision.feature.solution.api

interface SolutionRepository {

    suspend fun get(): List<Solution>

    suspend fun create(): Solution

    suspend fun create(description: String): Solution

    suspend fun changeDescription(id: Int, newDescription: String)

    suspend fun select(id: Int)

    suspend fun delete(id: Int)
}