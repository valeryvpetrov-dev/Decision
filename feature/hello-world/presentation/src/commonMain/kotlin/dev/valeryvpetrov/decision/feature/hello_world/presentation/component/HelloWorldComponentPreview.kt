package dev.valeryvpetrov.decision.feature.hello_world.presentation.component

import dev.valeryvpetrov.decision.feature.hello_world.presentation.mvi.HelloWorldIntent
import dev.valeryvpetrov.decision.feature.hello_world.presentation.mvi.HelloWorldState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HelloWorldComponentPreview : HelloWorldComponent {

    override val state: StateFlow<HelloWorldState> = MutableStateFlow(HelloWorldState.initial())

    override fun accept(intent: HelloWorldIntent) = Unit
}