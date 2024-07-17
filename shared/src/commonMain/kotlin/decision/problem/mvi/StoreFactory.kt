package decision.problem.mvi

import com.arkivanov.essenty.statekeeper.StateKeeper
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory as MviStoreFactory

class StoreFactory(
    private val storeFactory: MviStoreFactory,
    private val executor: Executor,
    private val reducer: Reducer,
) {

    fun create(stateKeeper: StateKeeper): Store<Intent, State, Label> {
        val initialState = stateKeeper.consume(
            key = State.STATE_KEEPER_KEY, strategy = State.serializer()
        ) ?: State.initial()
        return storeFactory.create(
            name = "Store",
            initialState = initialState,
            executorFactory = { executor },
            reducer = reducer
        ).also {
            stateKeeper.register(
                key = State.STATE_KEEPER_KEY, strategy = State.serializer()
            ) {
                it.state
            }
        }
    }
}