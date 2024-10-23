package dev.valeryvpetrov.decision.feature.hello_world.presentation.component

import com.arkivanov.mvikotlin.core.store.Store
import dev.valeryvpetrov.decision.base.presentation.PreviewComponentContext
import dev.valeryvpetrov.decision.base.presentation.createPreviewStore
import dev.valeryvpetrov.decision.feature.hello_world.presentation.mvi.HelloWorldIntent
import dev.valeryvpetrov.decision.feature.hello_world.presentation.mvi.HelloWorldState

class HelloWorldComponentPreview : HelloWorldComponent(
    componentContext = PreviewComponentContext
) {
    override val store: Store<HelloWorldIntent, HelloWorldState, Nothing>
        get() = createPreviewStore(HelloWorldState.initial())
}