package decision.solutions.mvi

import com.arkivanov.essenty.statekeeper.StateKeeper
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory

class StoreFactory(
    private val storeFactory: StoreFactory,
    private val executor: Executor,
    private val reducer: Reducer,
    private val bootstrapper: Bootstrapper,
) {

    fun create(stateKeeper: StateKeeper): Store<Intent, State, Label> {
        val initialState = stateKeeper.consume(
            key = State.STATE_KEEPER_KEY, strategy = State.serializer()
        ) ?: State.initial()
        return storeFactory.create(
            name = "Store",
            initialState = initialState,
            executorFactory = { executor },
            reducer = reducer,
            bootstrapper = bootstrapper,
        ).also {
            stateKeeper.register(
                key = State.STATE_KEEPER_KEY, strategy = State.serializer()
            ) {
                it.state
            }
        }
    }
}