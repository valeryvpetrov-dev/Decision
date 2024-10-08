package dev.valeryvpetrov.decision.feature.hello_world.presentation.mvi

sealed class HelloWorldMessage {

    data class OnChangeName(val name: String) : HelloWorldMessage()
    data class OnGreeting(val name: String) : HelloWorldMessage()
    data class OnGreetingSucceed(val greeting: String) : HelloWorldMessage()
    data class OnGreetingFailed(val message: String) : HelloWorldMessage()
}