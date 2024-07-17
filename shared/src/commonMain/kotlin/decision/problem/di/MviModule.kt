package decision.problem.di

import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import decision.di.Qualifier
import decision.problem.mvi.Executor
import decision.problem.mvi.Intent
import decision.problem.mvi.Label
import decision.problem.mvi.Reducer
import decision.problem.mvi.State
import decision.problem.mvi.StoreFactory
import org.koin.core.module.dsl.factoryOf
import org.koin.core.qualifier.named
import org.koin.dsl.module
import com.arkivanov.mvikotlin.core.store.StoreFactory as MviStoreFactory

internal val mviModule = module {
    factory<MviStoreFactory>(qualifier = named(Qualifier.MVI_STORE_FACTORY_PROBLEM)) {
        DefaultStoreFactory()
    }
    factory<StoreFactory> {
        object : StoreFactory {
            override fun create(): Store<Intent, State, Label> {
                val storeFactory = get<MviStoreFactory>(
                    qualifier = named(Qualifier.MVI_STORE_FACTORY_PROBLEM)
                )
                val executor = get<Executor>()
                val reducer = get<Reducer>()
                return storeFactory.create(
                    name = "Store",
                    initialState = State.initial(),
                    executorFactory = { executor },
                    reducer = reducer
                )
            }
        }
    }
    factoryOf(::Reducer)
    factoryOf(::Executor)
}