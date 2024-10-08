package dev.valeryvpetrov.decision.feature.hello_world.impl

import dev.valeryvpetrov.decision.feature.hello_world.api.GreetingUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class GreetingUseCaseImpl : GreetingUseCase {

    override suspend fun greet(name: String): String = withContext(Dispatchers.IO) {
        delay(3_000)
        "Hello, $name"
    }
}