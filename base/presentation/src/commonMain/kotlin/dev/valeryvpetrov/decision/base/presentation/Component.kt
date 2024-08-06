package dev.valeryvpetrov.decision.base.presentation

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface Component<State, Intent, Label> {

    val state: StateFlow<State>
    val labels: Flow<Label>

    fun accept(intent: Intent)
}