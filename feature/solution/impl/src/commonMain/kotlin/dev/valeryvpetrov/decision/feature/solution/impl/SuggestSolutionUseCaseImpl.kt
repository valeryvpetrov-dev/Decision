package dev.valeryvpetrov.decision.feature.solution.impl

import dev.valeryvpetrov.decision.feature.chat_gpt.api.ChatGptRepository
import dev.valeryvpetrov.decision.feature.make_decision.api.MakeDecisionRepository
import dev.valeryvpetrov.decision.feature.solution.api.Solution
import dev.valeryvpetrov.decision.feature.solution.api.SuggestSolutionUseCase

class SuggestSolutionUseCaseImpl(
    private val makeDecisionRepository: MakeDecisionRepository,
    private val chatGptRepository: ChatGptRepository,
) : SuggestSolutionUseCase {

    override suspend fun suggestSolution(currentSolutions: List<Solution>): Solution {
        val problem = makeDecisionRepository.getProblem() ?: error("No problem to suggest solution")
        return chatGptRepository.suggestSolution(problem, currentSolutions)
    }
}