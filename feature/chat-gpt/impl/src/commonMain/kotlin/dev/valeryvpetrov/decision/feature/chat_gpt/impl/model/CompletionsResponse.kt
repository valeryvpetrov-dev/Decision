package dev.valeryvpetrov.decision.feature.chat_gpt.impl.model

import kotlinx.serialization.Serializable

@Serializable
data class CompletionsResponse(
    val choices: List<Choice>,
)