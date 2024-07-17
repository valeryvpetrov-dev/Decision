package model

import kotlinx.serialization.Serializable

@Serializable
data class Problem(val description: String) {

    companion object {

        fun empty() = Problem("")
    }
}