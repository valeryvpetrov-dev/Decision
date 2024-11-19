package dev.valeryvpetrov.decision.feature.hello_world.presentation.mvi

import com.arkivanov.essenty.statekeeper.StateKeeper
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineExecutorFactory
import dev.valeryvpetrov.decision.base.presentation.resources.StringResources
import dev.valeryvpetrov.decision.feature.hello_world.api.GreetingUseCase
import dev.valeryvpetrov.decision.feature.hello_world.presentation.MR
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
    val nameTextField: TextFieldState,
    val greetingButtonState: ButtonState,
    val greeting: String?,
) {

    companion object {

        val STATE_KEEPER_KEY = "${this::class.qualifiedName}"

        fun initial(
            nameTextField: TextFieldState,
            greetingButtonState: ButtonState,
            greeting: String?,
        ) = HelloWorldState(
            nameTextField = nameTextField,
            greetingButtonState = greetingButtonState,
            greeting = greeting,
        )
    }

    @Serializable
    data class TextFieldState(
        val value: String,
        val label: String,
        val placeholder: String,
    )

    @Serializable
    data class ButtonState(
        val text: String,
        val isEnabled: Boolean,
    )
}

class HelloWorldStoreFactory(
    private val storeName: String,
    private val storeFactory: StoreFactory,
    private val greetingUseCase: GreetingUseCase,
) {

    fun create(
        stateKeeper: StateKeeper,
        stringResources: StringResources,
        initialState: HelloWorldState,
    ): Store<HelloWorldIntent, HelloWorldState, Nothing> {
        val state = stateKeeper.consume(
            key = HelloWorldState.STATE_KEEPER_KEY, strategy = HelloWorldState.serializer()
        ) ?: initialState
        return storeFactory.create<HelloWorldIntent, Nothing, HelloWorldMessage, HelloWorldState, Nothing>(
            name = storeName,
            initialState = state,
            executorFactory = coroutineExecutorFactory {
                onIntent<HelloWorldIntent.Greeting> {
                    launch {
                        val name = state().nameTextField.value
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
                        nameTextField = nameTextField.copy(
                            value = msg.name
                        ),
                        greetingButtonState = greetingButtonState.copy(
                            isEnabled = isGreetingButtonEnabled(msg.name)
                        ),
                        greeting = null
                    )

                    is HelloWorldMessage.OnGreeting -> copy(
                        nameTextField = nameTextField.copy(
                            value = msg.name
                        ),
                        greetingButtonState = greetingButtonState.copy(
                            isEnabled = false
                        ),
                        greeting = stringResources.getString(MR.strings.greeting_loading)
                    )

                    is HelloWorldMessage.OnGreetingFailed -> copy(
                        greetingButtonState = greetingButtonState.copy(
                            isEnabled = true
                        ),
                        greeting = msg.message
                    )

                    is HelloWorldMessage.OnGreetingSucceed -> copy(
                        greetingButtonState = greetingButtonState.copy(
                            isEnabled = true
                        ),
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