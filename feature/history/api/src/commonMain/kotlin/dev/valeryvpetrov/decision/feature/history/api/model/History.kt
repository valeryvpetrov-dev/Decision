package dev.valeryvpetrov.decision.feature.history.api.model

import kotlinx.serialization.Serializable

@Serializable
data class History(
    val id: Int,
    val decisionMessage: String,
)