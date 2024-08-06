package dev.valeryvpetrov.decision.feature.chat_gpt.impl.model

import kotlinx.serialization.Serializable

@Serializable
data class Message(
    val role: Role,
    val content: String,
)