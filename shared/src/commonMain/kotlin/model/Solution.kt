package model

import kotlinx.serialization.Serializable

@Serializable
data class Solution(
    val description: String,
    val isSelected: Boolean,
) {
    
    companion object {
        fun empty() = Solution("", false)
    }
}