package dev.valeryvpetrov.decision.feature.hello_world.presentation.mvi

import com.arkivanov.essenty.statekeeper.StateKeeper
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineExecutorFactory
import dev.valeryvpetrov.decision.feature.hello_world.api.GreetingUseCase
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable

/**
 * External contract
 */
sealed class HelloWorldIntent {

    data class ChangeName(val name: String) : HelloWorldIntent()
    data object Greeting : HelloWorldIntent()
}

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

class HelloWorldStoreFactory(
    private val storeName: String,
    private val storeFactory: StoreFactory,
    private val greetingUseCase: GreetingUseCase,
) {

    fun create(
        stateKeeper: StateKeeper,
    ): Store<HelloWorldIntent, HelloWorldState, Nothing> {
        val initialState = stateKeeper.consume(
            key = HelloWorldState.STATE_KEEPER_KEY, strategy = HelloWorldState.serializer()
        ) ?: HelloWorldState.initial()
        return storeFactory.create<HelloWorldIntent, Nothing, HelloWorldMessage, HelloWorldState, Nothing>(
            name = storeName,
            initialState = initialState,
            executorFactory = coroutineExecutorFactory {
                onIntent<HelloWorldIntent.Greeting> {
                    launch {
                        val name = state().name
                        dispatch(HelloWorldMessage.OnGreeting(name))
                        try {
                            val greeting = greetingUseCase.greet(name)
                            dispatch(HelloWorldMessage.OnGreetingSucceed(greeting))
                        } catch (e: Exception) {
                            dispatch(HelloWorldMessage.OnGreetingFailed(e.message ?: "Error"))
                        }
                    }
                }
                onIntent<HelloWorldIntent.ChangeName> { intent ->
                    dispatch(HelloWorldMessage.OnChangeName(intent.name))
                }
            },
            reducer = { msg ->
                fun isGreetingButtonEnabled(name: String): Boolean = name.isNotBlank()

                when (msg) {
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
            }
        ).also {
            stateKeeper.register(
                key = HelloWorldState.STATE_KEEPER_KEY, strategy = HelloWorldState.serializer()
            ) {
                it.state
            }
        }
    }
}

/**
 * Internal contract
 */
private sealed class HelloWorldMessage {

    data class OnChangeName(val name: String) : HelloWorldMessage()
    data class OnGreeting(val name: String) : HelloWorldMessage()
    data class OnGreetingSucceed(val greeting: String) : HelloWorldMessage()
    data class OnGreetingFailed(val message: String) : HelloWorldMessage()
}