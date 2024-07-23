package dev.valeryvpetrov.decision.domain

import kotlinx.serialization.Serializable

@Serializable
data class Problem(val description: String) {

    companion object {

        fun empty() = Problem("")
    }
}