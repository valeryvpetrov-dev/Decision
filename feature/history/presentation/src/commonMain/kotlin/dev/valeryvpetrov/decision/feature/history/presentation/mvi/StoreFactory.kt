package dev.valeryvpetrov.decision.feature.history.presentation.mvi

import com.arkivanov.essenty.statekeeper.StateKeeper
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory as MviStoreFactory

class StoreFactory(
    private val storeFactory: MviStoreFactory,
    private val storeName: String,
    private val executor: Executor,
    private val bootstrapper: Bootstrapper,
    private val reducer: Reducer,
) {

    fun create(
        stateKeeper: StateKeeper,
    ): Store<HistoryIntent, HistoryState, Nothing> {
        val initialState = stateKeeper.consume(
            key = HistoryState.STATE_KEEPER_KEY, strategy = HistoryState.serializer()
        ) ?: HistoryState.initial()
        return storeFactory.create(
            name = storeName,
            initialState = initialState,
            executorFactory = { executor },
            bootstrapper = bootstrapper,
            reducer = reducer,
        ).also {
            stateKeeper.register(
                key = HistoryState.STATE_KEEPER_KEY, strategy = HistoryState.serializer()
            ) {
                it.state
            }
        }
    }
}