package dev.valeryvpetrov.decision.feature.hello_world.presentation.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import dev.valeryvpetrov.decision.base.api.Provider
import dev.valeryvpetrov.decision.base.presentation.resources.StringResources
import dev.valeryvpetrov.decision.feature.hello_world.presentation.MR
import dev.valeryvpetrov.decision.feature.hello_world.presentation.mvi.HelloWorldIntent
import dev.valeryvpetrov.decision.feature.hello_world.presentation.mvi.HelloWorldState
import dev.valeryvpetrov.decision.feature.hello_world.presentation.mvi.HelloWorldStoreFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow

class HelloWorldComponentImpl(
    componentContext: ComponentContext,
    private val stringResources: StringResources,
    storeFactoryProvider: Provider<HelloWorldStoreFactory>,
) : ComponentContext by componentContext, HelloWorldComponent(
    componentContext = componentContext
) {

    class Factory(
        private val storeFactoryProvider: Provider<HelloWorldStoreFactory>,
        private val stringResources: StringResources,
    ) : HelloWorldComponent.Factory {

        override fun create(
            componentContext: ComponentContext,
        ): HelloWorldComponent = HelloWorldComponentImpl(
            componentContext = componentContext,
            stringResources = stringResources,
            storeFactoryProvider = storeFactoryProvider,
        )
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    override val state: StateFlow<HelloWorldState> by lazy { store.stateFlow }

    override fun accept(intent: HelloWorldIntent) = store.accept(intent)

    override val store: Store<HelloWorldIntent, HelloWorldState, Nothing> =
        instanceKeeper.getStore {
            storeFactoryProvider.get().create(
                stateKeeper = stateKeeper,
                stringResources = stringResources,
                initialState = HelloWorldState(
                    nameTextField = HelloWorldState.TextFieldState(
                        value = stringResources.getString(MR.strings.name_text_field_value_default),
                        label = stringResources.getString(MR.strings.name_text_field_label),
                        placeholder = stringResources.getString(MR.strings.name_text_field_placeholder),
                    ),
                    greetingButtonState = HelloWorldState.ButtonState(
                        text = stringResources.getString(MR.strings.greeting_button_text),
                        isEnabled = false,
                    ),
                    greeting = stringResources.getString(MR.strings.greeting_placeholder)
                )
            )
        }
}