package dev.valeryvpetrov.decision.feature.hello_world.presentation.mvi

import com.arkivanov.mvikotlin.core.store.Reducer as MviReducer

class HelloWorldReducer : MviReducer<HelloWorldState, HelloWorldMessage> {

    override fun HelloWorldState.reduce(msg: HelloWorldMessage): HelloWorldState = when (msg) {
        is HelloWorldMessage.OnChangeName -> copy(
            name = msg.name,
            isGreetingButtonEnabled = isGreetingButtonEnabled(msg.name),
            greeting = null
        )

        is HelloWorldMessage.OnGreeting -> copy(
            name = msg.name,
            isGreetingButtonEnabled = false,
            greeting = null
        )

        is HelloWorldMessage.OnGreetingFailed -> copy(
            isGreetingButtonEnabled = true,
            greeting = msg.message
        )

        is HelloWorldMessage.OnGreetingSucceed -> copy(
            isGreetingButtonEnabled = true,
            greeting = msg.greeting
        )
    }

    private fun isGreetingButtonEnabled(name: String): Boolean = name.isNotBlank()
}
