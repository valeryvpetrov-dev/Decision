package dev.valeryvpetrov.decision.feature.decision.di

import dev.valeryvpetrov.decision.base.api.Provider
import dev.valeryvpetrov.decision.feature.decision.impl.mvi.Executor
import dev.valeryvpetrov.decision.feature.decision.impl.mvi.Reducer
import dev.valeryvpetrov.decision.feature.decision.impl.mvi.StoreFactory
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import com.arkivanov.mvikotlin.core.store.StoreFactory as MviStoreFactory
import dev.valeryvpetrov.decision.base.di.Qualifier.Feature.Decision as FeatureQualifier

internal val mviModule = module {
    factory<Provider<StoreFactory>>(qualifier = FeatureQualifier.StoreFactoryProvider.qualifier) {
        object : Provider<StoreFactory> {
            override fun get(): StoreFactory = get<StoreFactory>()
        }
    }
    factory<StoreFactory> {
        StoreFactory(
            storeFactory = get<MviStoreFactory>(),
            storeName = get<String>(qualifier = FeatureQualifier.StoreName.qualifier),
            executorFactory = get<Executor.Factory>(),
            reducer = get<Reducer>(),
        )
    }
    factory<String>(qualifier = FeatureQualifier.StoreName.qualifier) {
        FeatureQualifier.StoreName.name
    }
    factory<Executor.Factory> {
        object : Executor.Factory {
            override fun create(onGoToSolutions: () -> Unit, onRestart: () -> Unit) = Executor(
                onGoToSolutions = onGoToSolutions,
                onRestart = onRestart,
            )
        }
    }
    factoryOf(::Reducer)
}