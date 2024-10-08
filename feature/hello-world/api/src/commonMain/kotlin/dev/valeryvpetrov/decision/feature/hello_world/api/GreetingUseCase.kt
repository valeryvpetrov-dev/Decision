package dev.valeryvpetrov.decision.feature.hello_world.api

interface GreetingUseCase {

    suspend fun greet(name: String): String
}