package dev.valeryvpetrov.decision.feature.chat_gpt.impl.api

import dev.valeryvpetrov.decision.feature.chat_gpt.impl.model.CompletionsRequest
import dev.valeryvpetrov.decision.feature.chat_gpt.impl.model.CompletionsResponse
import dev.valeryvpetrov.decision.feature.chat_gpt.impl.model.Message
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType

class ChatGptApi(
    private val httpClient: HttpClient,
    private val chatGptModel: String,
) {

    /**
     * @see https://platform.openai.com/docs/api-reference/chat
     */
    suspend fun completions(
        messages: List<Message>,
    ): CompletionsResponse {
        val request = CompletionsRequest(
            model = chatGptModel,
            messages = messages
        )
        val httpResponse: HttpResponse = httpClient.post("chat/completions") {
            contentType(ContentType.Application.Json)
            setBody(request)
        }
        return httpResponse.body()
    }
}
