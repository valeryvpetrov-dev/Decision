package dev.valeryvpetrov.decision.feature.chat_gpt.api.repository

import dev.valeryvpetrov.decision.feature.chat_gpt.api.model.Message

interface ChatGptRepository {

    suspend fun suggestSolution(messages: List<Message>): List<Message>
}