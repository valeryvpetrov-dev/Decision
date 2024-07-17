package decision.problem.di

import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import decision.di.Qualifier
import decision.problem.mvi.Executor
import decision.problem.mvi.Reducer
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
        StoreFactory(
            storeFactory = get(qualifier = named(Qualifier.MVI_STORE_FACTORY_PROBLEM)),
            executor = get(),
            reducer = get(),
        )
    }
    factoryOf(::Reducer)
    factoryOf(::Executor)
}