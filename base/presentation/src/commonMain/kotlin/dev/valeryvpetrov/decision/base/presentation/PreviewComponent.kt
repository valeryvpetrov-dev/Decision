package dev.valeryvpetrov.decision.base.presentation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineExecutorFactory
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory

object PreviewComponentContext : ComponentContext by DefaultComponentContext(
    lifecycle = LifecycleRegistry(),
)

fun <Intent : Any, State : Any, Label : Any> createPreviewStore(stateInitial: State): Store<Intent, State, Label> {
    return DefaultStoreFactory().create(
        initialState = stateInitial,
        executorFactory = coroutineExecutorFactory {}
    )
}