package dev.valeryvpetrov.decision.base.presentation

import com.arkivanov.decompose.value.Value
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface Component<State : Any, Intent : Any, Label : Any> {

    val state: StateFlow<State>
    val labels: Flow<Label>

    val stateValue: Value<State>

    fun accept(intent: Intent)
}