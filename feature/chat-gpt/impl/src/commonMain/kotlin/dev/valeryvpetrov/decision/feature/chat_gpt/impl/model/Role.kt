package dev.valeryvpetrov.decision.feature.chat_gpt.impl.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class Role {
    @SerialName("user")
    USER,

    @SerialName("assistant")
    ASSISTANT,

    @SerialName("system")
    SYSTEM,

    @SerialName("tool")
    TOOL,

    @SerialName("function")
    FUNCTION,
}