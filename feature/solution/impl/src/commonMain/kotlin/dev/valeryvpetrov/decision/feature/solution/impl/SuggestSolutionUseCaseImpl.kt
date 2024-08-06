package dev.valeryvpetrov.decision.feature.solution.impl

import dev.valeryvpetrov.decision.feature.chat_gpt.api.ChatGptRepository
import dev.valeryvpetrov.decision.feature.make_decision.api.DecisionRepository
import dev.valeryvpetrov.decision.feature.solution.api.Solution
import dev.valeryvpetrov.decision.feature.solution.api.SuggestSolutionUseCase

class SuggestSolutionUseCaseImpl(
    private val decisionRepository: DecisionRepository,
    private val chatGptRepository: ChatGptRepository,
) : SuggestSolutionUseCase {

    override suspend fun suggestSolution(currentSolutions: List<Solution>): Solution {
        val problem = decisionRepository.getProblem() ?: error("No problem to suggest solution")
        return chatGptRepository.suggestSolution(problem, currentSolutions)
    }
}