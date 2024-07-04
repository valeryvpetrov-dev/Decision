package model

data class Solution(
    val description: String,
    val isSelected: Boolean,
) {
    
    companion object {
        fun empty() = Solution("", false)
    }
}