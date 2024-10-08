package dev.valeryvpetrov.decision.feature.hello_world.presentation.mvi

import kotlinx.serialization.Serializable

@Serializable
data class HelloWorldState(
    val name: String,
    val isGreetingButtonEnabled: Boolean,
    val greeting: String?,
) {

    companion object {

        val STATE_KEEPER_KEY = "${this::class.qualifiedName}"

        fun initial() = HelloWorldState(
            name = "",
            isGreetingButtonEnabled = false,
            greeting = null,
        )
    }
}
