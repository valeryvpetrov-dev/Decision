package dev.valeryvpetrov.decision.feature.hello_world.presentation.mvi

import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import dev.valeryvpetrov.decision.feature.hello_world.api.GreetingUseCase
import kotlinx.coroutines.launch

class HelloWorldExecutor(
    private val greetingUseCase: GreetingUseCase,
) : CoroutineExecutor<HelloWorldIntent, Nothing, HelloWorldState, HelloWorldMessage, Nothing>() {

    // TODO: test case with many intents without disabled button?
    override fun executeIntent(intent: HelloWorldIntent) {
        when (intent) {
            HelloWorldIntent.Greeting -> scope.launch {
                val name = state().name
                dispatch(HelloWorldMessage.OnGreeting(name))
                try {
                    val greeting = greetingUseCase.greet(name)
                    dispatch(HelloWorldMessage.OnGreetingSucceed(greeting))
                } catch (e: Exception) {
                    dispatch(HelloWorldMessage.OnGreetingFailed(e.message ?: "Error"))
                }
            }

            is HelloWorldIntent.ChangeName -> dispatch(HelloWorldMessage.OnChangeName(intent.name))
        }
    }
}