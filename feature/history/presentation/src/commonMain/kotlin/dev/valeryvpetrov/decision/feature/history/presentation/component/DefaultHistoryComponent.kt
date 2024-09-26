package dev.valeryvpetrov.decision.feature.history.presentation.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.core.store.Store
import dev.valeryvpetrov.decision.base.api.Provider
import dev.valeryvpetrov.decision.feature.history.presentation.mvi.HistoryIntent
import dev.valeryvpetrov.decision.feature.history.presentation.mvi.HistoryState
import dev.valeryvpetrov.decision.feature.history.presentation.mvi.StoreFactory

class DefaultHistoryComponent(
    componentContext: ComponentContext,
    private val storeFactoryProvider: Provider<StoreFactory>,
) : ComponentContext by componentContext, HistoryComponent(componentContext = componentContext) {

    class Factory(
        private val storeFactoryProvider: Provider<StoreFactory>,
    ) : HistoryComponent.Factory {

        override fun create(
            componentContext: ComponentContext,
        ): HistoryComponent = DefaultHistoryComponent(
            componentContext = componentContext,
            storeFactoryProvider = storeFactoryProvider,
        )
    }

    override val store: Store<HistoryIntent, HistoryState, Nothing> =
        instanceKeeper.getStore {
            storeFactoryProvider.get().create(
                stateKeeper = stateKeeper,
            )
        }
}