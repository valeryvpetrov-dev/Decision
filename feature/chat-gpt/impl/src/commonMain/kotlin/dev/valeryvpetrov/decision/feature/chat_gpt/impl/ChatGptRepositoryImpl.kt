package dev.valeryvpetrov.decision.feature.chat_gpt.impl

import dev.valeryvpetrov.decision.feature.chat_gpt.api.ChatGptRepository
import dev.valeryvpetrov.decision.feature.chat_gpt.impl.api.ChatGptApi
import dev.valeryvpetrov.decision.feature.chat_gpt.impl.model.Message
import dev.valeryvpetrov.decision.feature.chat_gpt.impl.model.Role
import dev.valeryvpetrov.decision.feature.problem.api.Problem
import dev.valeryvpetrov.decision.feature.solution.api.Solution

class ChatGptRepositoryImpl(
    private val chatGptApi: ChatGptApi,
) : ChatGptRepository {

    override suspend fun suggestSolution(
        problem: Problem,
        solutions: List<Solution>,
    ): Solution {
        val messages = listOf(
            Message(
                role = Role.USER,
                content = """
                    I have problem: "$problem.description". 
                    I also have solution variants "${solutions.joinToString { it.description }}".
                    Suggest new solution. Write only solution without extra words.
                """.trimIndent()
            ),
        )
        val description = chatGptApi.completions(messages)
            .choices.firstOrNull()?.message?.content ?: ""
        return Solution(description = description, isSelected = false)
    }
}