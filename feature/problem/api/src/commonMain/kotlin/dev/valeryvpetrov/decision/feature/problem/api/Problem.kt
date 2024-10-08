package dev.valeryvpetrov.decision.feature.problem.api

import kotlinx.serialization.Serializable

@Serializable
data class Problem(val description: String) {

    companion object {

        fun empty() = Problem("")
    }
}