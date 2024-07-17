package decision.mvi

import com.arkivanov.essenty.statekeeper.StateKeeper
import com.arkivanov.mvikotlin.core.store.Store
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.parameter.parametersOf
import com.arkivanov.mvikotlin.core.store.StoreFactory as MviStoreFactory

class StoreFactory(
    private val storeFactory: MviStoreFactory,
    private val bootstrapper: Bootstrapper,
) : KoinComponent {

    fun create(stateKeeper: StateKeeper): Store<Nothing, State, Nothing> {
        val initialState = stateKeeper.consume(
            key = State.STATE_KEEPER_KEY, strategy = State.serializer()
        ) ?: State.initial()
        val executor = get<Executor> {
            parametersOf(
                initialState
            )
        }
        return storeFactory.create(
            name = "Store",
            initialState = initialState,
            bootstrapper = bootstrapper,
            executorFactory = { executor },
        ).also {
            stateKeeper.register(
                key = State.STATE_KEEPER_KEY, strategy = State.serializer()
            ) {
                it.state
            }
        }
    }
}