package decision.solutions.di

import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import decision.di.Qualifier
import decision.solutions.mvi.Executor
import decision.solutions.mvi.Intent
import decision.solutions.mvi.Label
import decision.solutions.mvi.Reducer
import decision.solutions.mvi.State
import org.koin.core.module.dsl.factoryOf
import org.koin.core.qualifier.named
import org.koin.dsl.module
import com.arkivanov.mvikotlin.core.store.Store as MviStore
import com.arkivanov.mvikotlin.core.store.StoreFactory as MviStoreFactory

internal val mviModule = module {
    factory<MviStoreFactory> {
        DefaultStoreFactory()
    }
    factory<MviStore<Intent, State, Label>>(qualifier = named(Qualifier.MVI_STORE_SOLUTIONS)) {
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