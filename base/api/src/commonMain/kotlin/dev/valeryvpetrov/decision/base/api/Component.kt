package dev.valeryvpetrov.decision.base.api

import kotlinx.coroutines.flow.StateFlow

typealias BaseComponent<State, Intent> = Component<State, Intent>

interface Component<State, Intent> {

    val state: StateFlow<State>

    fun accept(intent: Intent)
}