package dev.valeryvpetrov.decision.feature.make_decision.di

import dev.valeryvpetrov.decision.base.api.Provider
import dev.valeryvpetrov.decision.base.di.Qualifier.Feature.MakeDecision
import dev.valeryvpetrov.decision.data.api.DecisionRepository
import dev.valeryvpetrov.decision.feature.make_decision.impl.mvi.Bootstrapper
import dev.valeryvpetrov.decision.feature.make_decision.impl.mvi.Executor
import dev.valeryvpetrov.decision.feature.make_decision.impl.mvi.StoreFactory
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import com.arkivanov.mvikotlin.core.store.StoreFactory as MviStoreFactory

internal val mviModule = module {
    factory<Provider<StoreFactory>>(qualifier = MakeDecision.StoreFactoryProvider.qualifier) {
        object : Provider<StoreFactory> {
            override fun get(): StoreFactory = get<StoreFactory>()
        }
    }
    factory<StoreFactory> {
        StoreFactory(
            storeFactory = get<MviStoreFactory>(),
            storeName = get<String>(qualifier = MakeDecision.StoreName.qualifier),
            bootstrapper = get<Bootstrapper>(),
            executorFactory = get<Executor.Factory>(),
        )
    }
    factory<String>(qualifier = MakeDecision.StoreName.qualifier) {
        MakeDecision.StoreName.name
    }
    factory<Executor.Factory> {
        Executor.Factory(
            decisionRepository = get<DecisionRepository>()
        )
    }
    factoryOf(::Bootstrapper)
}