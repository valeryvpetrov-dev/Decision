package dev.valeryvpetrov.decision.base.api

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

typealias BaseComponent<State, Label, Intent> = Component<State, Label, Intent>

interface Component<State, Label, Intent> {

    val state: StateFlow<State>
    val labels: Flow<Label>

    fun accept(intent: Intent)
}