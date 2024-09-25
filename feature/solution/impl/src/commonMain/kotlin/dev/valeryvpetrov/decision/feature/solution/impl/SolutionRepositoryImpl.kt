package dev.valeryvpetrov.decision.feature.solution.impl

import dev.valeryvpetrov.decision.feature.solution.api.Solution
import dev.valeryvpetrov.decision.feature.solution.api.SolutionRepository

class SolutionRepositoryImpl : SolutionRepository {

    private var lastIndex: Int = 0
    private val solutions = mutableListOf<Solution>()

    override suspend fun get(): List<Solution> = solutions

    override suspend fun create(): Solution = create(description = "")

    override suspend fun create(description: String): Solution {
        val newSolution = Solution(id = lastIndex, description = description, isSelected = false)
        lastIndex++
        solutions.add(newSolution)
        return newSolution
    }

    override suspend fun changeDescription(id: Int, newDescription: String) {
        val idx = solutions.indexOfFirst { it.id == id }
        val updated = solutions[idx].copy(description = newDescription)
        solutions.removeAt(idx)
        solutions.add(idx, updated)
    }

    override suspend fun select(id: Int) {
        val updated = solutions.map { solution ->
            if (solution.id == id) solution.copy(isSelected = true)
            else if (solution.isSelected) solution.copy(isSelected = false)
            else solution
        }
        solutions.clear()
        solutions.addAll(updated)
    }

    override suspend fun delete(id: Int) {
        val idx = solutions.indexOfFirst { it.id == id }
        solutions.removeAt(idx)
    }
}