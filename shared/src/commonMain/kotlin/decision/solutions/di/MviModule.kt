package decision.solutions.di

import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import decision.di.Qualifier
import decision.solutions.mvi.Bootstrapper
import decision.solutions.mvi.Executor
import decision.solutions.mvi.Reducer
import decision.solutions.mvi.StoreFactory
import org.koin.core.module.dsl.factoryOf
import org.koin.core.qualifier.named
import org.koin.dsl.module
import com.arkivanov.mvikotlin.core.store.StoreFactory as MviStoreFactory

internal val mviModule = module {
    factory<MviStoreFactory>(qualifier = named(Qualifier.MVI_STORE_FACTORY_SOLUTIONS)) {
        DefaultStoreFactory()
    }
    factory<StoreFactory> {
        StoreFactory(
            storeFactory = get(qualifier = named(Qualifier.MVI_STORE_FACTORY_SOLUTIONS)),
            executor = get(),
            reducer = get(),
            bootstrapper = get(),
        )
    }
    factoryOf(::Reducer)
    factoryOf(::Executor)
    factoryOf(::Bootstrapper)
}