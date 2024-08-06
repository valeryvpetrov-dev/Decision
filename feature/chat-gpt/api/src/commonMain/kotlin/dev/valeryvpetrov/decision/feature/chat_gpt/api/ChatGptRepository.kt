package dev.valeryvpetrov.decision.feature.chat_gpt.api

import dev.valeryvpetrov.decision.feature.problem.api.Problem
import dev.valeryvpetrov.decision.feature.solution.api.Solution

interface ChatGptRepository {

    suspend fun suggestSolution(problem: Problem, solutions: List<Solution>): Solution
}