package dev.valeryvpetrov.decision.feature.tabs.presentation.component

import kotlinx.serialization.Serializable

@Serializable
sealed class Config {

    @Serializable
    data object MakeDecision : Config()

    @Serializable
    data object History : Config()
}