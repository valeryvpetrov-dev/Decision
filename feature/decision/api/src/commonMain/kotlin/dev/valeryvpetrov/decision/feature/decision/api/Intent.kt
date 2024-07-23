package dev.valeryvpetrov.decision.feature.decision.api

sealed class Intent {

    data object GoToSolutions : Intent()
    data object Restart : Intent()
}