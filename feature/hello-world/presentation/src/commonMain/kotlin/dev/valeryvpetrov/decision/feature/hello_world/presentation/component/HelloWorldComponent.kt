package dev.valeryvpetrov.decision.feature.hello_world.presentation.component

import com.arkivanov.decompose.ComponentContext
import dev.valeryvpetrov.decision.feature.hello_world.presentation.mvi.HelloWorldIntent
import dev.valeryvpetrov.decision.feature.hello_world.presentation.mvi.HelloWorldState
import kotlinx.coroutines.flow.StateFlow

interface HelloWorldComponent {

    interface Factory {

        fun create(componentContext: ComponentContext): HelloWorldComponent
    }

    val state: StateFlow<HelloWorldState>

    fun accept(intent: HelloWorldIntent)
}
