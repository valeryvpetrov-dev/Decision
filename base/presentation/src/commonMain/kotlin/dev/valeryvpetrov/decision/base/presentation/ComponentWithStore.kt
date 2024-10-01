package dev.valeryvpetrov.decision.base.presentation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import dev.valeryvpetrov.decision.base.presentation.mvi.stateAsValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

abstract class ComponentWithStore<State : Any, Intent : Any, Label : Any>(
    protected val componentContext: ComponentContext,
) : Component<State, Intent, Label> {

    protected abstract val store: Store<Intent, State, Label>

    @OptIn(ExperimentalCoroutinesApi::class)
    override val state: StateFlow<State> by lazy { store.stateFlow }
    override val labels: Flow<Label> by lazy { store.labels }

    // TODO: replace StateFlow state property with stateValue
    override val stateValue: Value<State> by lazy { store.stateAsValue() }

    override fun accept(intent: Intent) = store.accept(intent)
}