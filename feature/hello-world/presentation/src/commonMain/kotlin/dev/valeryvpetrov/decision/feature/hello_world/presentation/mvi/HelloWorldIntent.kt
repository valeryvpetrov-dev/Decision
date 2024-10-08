package dev.valeryvpetrov.decision.feature.hello_world.presentation.mvi

sealed class HelloWorldIntent {

    data class ChangeName(val name: String) : HelloWorldIntent()
    data object Greeting : HelloWorldIntent()
}