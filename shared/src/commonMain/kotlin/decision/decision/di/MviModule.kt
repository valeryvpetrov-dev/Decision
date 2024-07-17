package decision.decision.di

import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import decision.decision.mvi.Bootstrapper
import decision.decision.mvi.Executor
import decision.decision.mvi.Reducer
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
        StoreFactory(
            storeFactory = get(qualifier = named(Qualifier.MVI_STORE_FACTORY_DECISION)),
            executor = get(),
            reducer = get(),
            bootstrapper = get(),
        )
    }
    factoryOf(::Reducer)
    factoryOf(::Executor)
    factoryOf(::Bootstrapper)
}