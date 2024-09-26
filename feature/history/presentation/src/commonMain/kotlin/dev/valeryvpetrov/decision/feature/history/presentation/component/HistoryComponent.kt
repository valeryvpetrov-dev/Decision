package dev.valeryvpetrov.decision.feature.history.presentation.component

import com.arkivanov.decompose.ComponentContext
import dev.valeryvpetrov.decision.base.presentation.ComponentWithStore
import dev.valeryvpetrov.decision.feature.history.presentation.mvi.HistoryIntent
import dev.valeryvpetrov.decision.feature.history.presentation.mvi.HistoryState

abstract class HistoryComponent(
    componentContext: ComponentContext,
) : ComponentWithStore<HistoryState, HistoryIntent, Nothing>(
    componentContext = componentContext
) {

    interface Factory {

        fun create(
            componentContext: ComponentContext,
        ): HistoryComponent
    }
}