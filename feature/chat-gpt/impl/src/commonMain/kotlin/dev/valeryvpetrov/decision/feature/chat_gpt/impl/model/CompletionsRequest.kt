package dev.valeryvpetrov.decision.feature.chat_gpt.impl.model

import kotlinx.serialization.Serializable

@Serializable
data class CompletionsRequest(
    val model: String,
    val messages: List<Message>,
)