package dev.valeryvpetrov.decision.feature.hello_world.presentation.component

import com.arkivanov.decompose.ComponentContext
import dev.valeryvpetrov.decision.base.presentation.ComponentWithStore
import dev.valeryvpetrov.decision.feature.hello_world.presentation.mvi.HelloWorldIntent
import dev.valeryvpetrov.decision.feature.hello_world.presentation.mvi.HelloWorldState

abstract class HelloWorldComponent(
    componentContext: ComponentContext,
) : ComponentWithStore<HelloWorldState, HelloWorldIntent, Nothing>(
    componentContext = componentContext,
) {

    interface Factory {

        fun create(componentContext: ComponentContext): HelloWorldComponent
    }
}
