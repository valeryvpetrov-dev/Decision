package decision.di

import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import decision.mvi.Bootstrapper
import decision.mvi.Executor
import decision.mvi.State
import decision.mvi.StoreFactory
import org.koin.core.module.dsl.factoryOf
import org.koin.core.qualifier.named
import org.koin.dsl.module
import com.arkivanov.mvikotlin.core.store.StoreFactory as MviStoreFactory

internal val mviModule = module {
    factory<MviStoreFactory>(qualifier = named(Qualifier.MVI_STORE_FACTORY_MAKE_DECISION)) {
        DefaultStoreFactory()
    }
    factory<StoreFactory> {
        StoreFactory(
            storeFactory = get(qualifier = named(Qualifier.MVI_STORE_FACTORY_MAKE_DECISION)),
            bootstrapper = get(),
        )
    }
    factory<Executor> { (savedState: State) ->
        Executor(
            savedState = savedState,
            decisionRepository = get()
        )
    }
    factoryOf(::Bootstrapper)
}