package dev.valeryvpetrov.decision.base.presentation

import kotlinx.coroutines.flow.StateFlow

typealias BaseComponent<State, Intent> = Component<State, Intent>

interface Component<State, Intent> {

    val state: StateFlow<State>

    fun accept(intent: Intent)
}