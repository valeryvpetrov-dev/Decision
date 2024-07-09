package mvi

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface Component<State, Label, Intent> {

    val state: StateFlow<State>
    val labels: Flow<Label>

    fun accept(intent: Intent)
}