package dev.valeryvpetrov.decision.feature.solution.di

import dev.valeryvpetrov.decision.base.api.Provider
import dev.valeryvpetrov.decision.base.di.Qualifier.Feature.Solution
import dev.valeryvpetrov.decision.feature.solution.impl.mvi.Bootstrapper
import dev.valeryvpetrov.decision.feature.solution.impl.mvi.Executor
import dev.valeryvpetrov.decision.feature.solution.impl.mvi.Reducer
import dev.valeryvpetrov.decision.feature.solution.impl.mvi.StoreFactory
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import com.arkivanov.mvikotlin.core.store.StoreFactory as MviStoreFactory

internal val mviModule = module {
    factory<Provider<StoreFactory>>(qualifier = Solution.StoreFactoryProvider.qualifier) {
        object : Provider<StoreFactory> {
            override fun get(): StoreFactory = get<StoreFactory>()
        }
    }
    factory<StoreFactory> {
        StoreFactory(
            storeFactory = get<MviStoreFactory>(),
            storeName = get<String>(qualifier = Solution.StoreName.qualifier),
            executor = get<Executor>(),
            reducer = get<Reducer>(),
            bootstrapper = get<Bootstrapper>(),
        )
    }
    factory<String>(qualifier = Solution.StoreName.qualifier) {
        Solution.StoreName.name
    }
    factoryOf(::Reducer)
    factoryOf(::Executor)
    factoryOf(::Bootstrapper)
}