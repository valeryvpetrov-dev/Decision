package dev.valeryvpetrov.decision.feature.chat_gpt.impl

import dev.valeryvpetrov.decision.feature.chat_gpt.api.repository.ChatGptRepository
import dev.valeryvpetrov.decision.feature.chat_gpt.impl.api.ChatGptApi
import dev.valeryvpetrov.decision.feature.chat_gpt.impl.model.Message
import dev.valeryvpetrov.decision.feature.chat_gpt.impl.model.Role
import dev.valeryvpetrov.decision.feature.chat_gpt.api.model.Message as DomainMessage

class ChatGptRepositoryImpl(
    private val chatGptApi: ChatGptApi,
) : ChatGptRepository {

    override suspend fun suggestSolution(messages: List<DomainMessage>): List<DomainMessage> {
        val messagesData = messages.map { message ->
            Message(
                role = Role.USER,
                content = message.text
            )
        }
        return chatGptApi.completions(messagesData)
            .choices
            .map { choice ->
                DomainMessage(choice.message.content)
            }
    }
}