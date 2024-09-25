package dev.valeryvpetrov.decision.feature.solution.impl

import dev.valeryvpetrov.decision.feature.chat_gpt.api.model.Message
import dev.valeryvpetrov.decision.feature.chat_gpt.api.repository.ChatGptRepository
import dev.valeryvpetrov.decision.feature.make_decision.api.MakeDecisionRepository
import dev.valeryvpetrov.decision.feature.solution.api.Solution
import dev.valeryvpetrov.decision.feature.solution.api.SolutionRepository
import dev.valeryvpetrov.decision.feature.solution.api.SuggestSolutionUseCase

class SuggestSolutionUseCaseImpl(
    private val makeDecisionRepository: MakeDecisionRepository,
    private val chatGptRepository: ChatGptRepository,
    private val solutionRepository: SolutionRepository,
) : SuggestSolutionUseCase {

    override suspend fun suggestSolution(): Solution {
        val problem = makeDecisionRepository.getProblem() ?: error("No problem to suggest solution")
        val currentSolutions = solutionRepository.get()
        val messages = listOf(
            Message(
                text = """
                    I have problem: "$problem.description". 
                    I also have solution variants "${currentSolutions.joinToString { it.description }}".
                    Suggest new solution. Write only solution without extra words.
                """.trimIndent()
            )
        )
        val description = chatGptRepository.suggestSolution(messages).firstOrNull()?.text ?: ""
        return solutionRepository.create(description)
    }
}