package dev.valeryvpetrov.decision.feature.decision.api

sealed class Label {

    data object GoToSolutions : Label()
    data object Restart : Label()
}