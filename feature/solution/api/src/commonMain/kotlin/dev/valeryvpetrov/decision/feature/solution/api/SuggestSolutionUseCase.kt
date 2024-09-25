package dev.valeryvpetrov.decision.feature.solution.api

interface SuggestSolutionUseCase {

    suspend fun suggestSolution(): Solution
}