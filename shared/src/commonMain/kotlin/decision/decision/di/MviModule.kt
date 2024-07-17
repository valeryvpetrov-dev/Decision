package decision.decision.di

import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import decision.decision.mvi.Bootstrapper
import decision.decision.mvi.Executor
import decision.decision.mvi.Intent
import decision.decision.mvi.Label
import decision.decision.mvi.Reducer
import decision.decision.mvi.State
import decision.decision.mvi.StoreFactory
import decision.di.Qualifier
import org.koin.core.module.dsl.factoryOf
import org.koin.core.qualifier.named
import org.koin.dsl.module
import com.arkivanov.mvikotlin.core.store.StoreFactory as MviStoreFactory

internal val mviModule = module {
    factory<MviStoreFactory>(qualifier = named(Qualifier.MVI_STORE_FACTORY_DECISION)) {
        DefaultStoreFactory()
    }
    factory<StoreFactory> {
        object : StoreFactory {
            override fun create(): Store<Intent, State, Label> {
                val storeFactory = get<MviStoreFactory>(
                    qualifier = named(Qualifier.MVI_STORE_FACTORY_DECISION)
                )
                val executor = get<Executor>()
                val reducer = get<Reducer>()
                val bootstrapper = get<Bootstrapper>()
                return storeFactory.create(
                    name = "Store",
                    initialState = State.initial(),
                    bootstrapper = bootstrapper,
                    executorFactory = { executor },
                    reducer = reducer,
                )
            }
        }
    }
    factoryOf(::Reducer)
    factoryOf(::Executor)
    factoryOf(::Bootstrapper)
}