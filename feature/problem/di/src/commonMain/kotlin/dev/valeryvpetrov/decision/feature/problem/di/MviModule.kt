package dev.valeryvpetrov.decision.feature.problem.di

import dev.valeryvpetrov.decision.base.api.Provider
import dev.valeryvpetrov.decision.base.di.Qualifier.Feature.Problem
import dev.valeryvpetrov.decision.feature.problem.impl.mvi.Executor
import dev.valeryvpetrov.decision.feature.problem.impl.mvi.Reducer
import dev.valeryvpetrov.decision.feature.problem.impl.mvi.StoreFactory
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import com.arkivanov.mvikotlin.core.store.StoreFactory as MviStoreFactory

internal val mviModule = module {
    factory<Provider<StoreFactory>>(qualifier = Problem.StoreFactoryProvider.qualifier) {
        object : Provider<StoreFactory> {
            override fun get(): StoreFactory = get<StoreFactory>()
        }
    }
    factory<StoreFactory> {
        StoreFactory(
            storeFactory = get<MviStoreFactory>(),
            storeName = get<String>(qualifier = Problem.StoreName.qualifier),
            executor = get<Executor>(),
            reducer = get<Reducer>(),
        )
    }
    factory<String>(qualifier = Problem.StoreName.qualifier) {
        Problem.StoreName.name
    }
    factoryOf(::Reducer)
    factoryOf(::Executor)
}