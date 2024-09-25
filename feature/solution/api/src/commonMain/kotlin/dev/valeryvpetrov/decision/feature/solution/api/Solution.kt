package dev.valeryvpetrov.decision.feature.solution.api

import kotlinx.serialization.Serializable

@Serializable
data class Solution(
    val id: Int,
    val description: String,
    val isSelected: Boolean,
)