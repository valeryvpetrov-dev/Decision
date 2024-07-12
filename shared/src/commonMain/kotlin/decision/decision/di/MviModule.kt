package decision.decision.di

import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import decision.decision.mvi.Executor
import decision.decision.mvi.Intent
import decision.decision.mvi.Label
import decision.decision.mvi.Reducer
import decision.decision.mvi.State
import decision.di.Qualifier
import org.koin.core.module.dsl.factoryOf
import org.koin.core.qualifier.named
import org.koin.dsl.module
import com.arkivanov.mvikotlin.core.store.Store as MviStore
import com.arkivanov.mvikotlin.core.store.StoreFactory as MviStoreFactory

internal val mviModule = module {
    factory<MviStoreFactory> {
        DefaultStoreFactory()
    }
    factory<MviStore<Intent, State, Label>>(qualifier = named(Qualifier.MVI_STORE_DECISION)) {
        val storeFactory = get<MviStoreFactory>()
        val executor = get<Executor>()
        val reducer = get<Reducer>()
        storeFactory.create(
            name = "Store",
            initialState = State.initial(),
            executorFactory = { executor },
            reducer = reducer
        )
    }
    factoryOf(::Reducer)
    factoryOf(::Executor)
}