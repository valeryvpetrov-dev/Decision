package dev.valeryvpetrov.decision.feature.history.di

import dev.valeryvpetrov.decision.base.api.Provider
import dev.valeryvpetrov.decision.feature.history.api.repository.HistoryRepository
import dev.valeryvpetrov.decision.feature.history.presentation.mvi.Bootstrapper
import dev.valeryvpetrov.decision.feature.history.presentation.mvi.Executor
import dev.valeryvpetrov.decision.feature.history.presentation.mvi.Reducer
import dev.valeryvpetrov.decision.feature.history.presentation.mvi.StoreFactory
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import com.arkivanov.mvikotlin.core.store.StoreFactory as MviStoreFactory
import dev.valeryvpetrov.decision.base.di.Qualifier.Feature.History as QualifierHistory

internal val mviModule = module {
    factory<Provider<StoreFactory>>(qualifier = QualifierHistory.StoreFactoryProvider.qualifier) {
        object : Provider<StoreFactory> {
            override fun get(): StoreFactory = get<StoreFactory>()
        }
    }
    factory<StoreFactory> {
        StoreFactory(
            storeFactory = get<MviStoreFactory>(),
            storeName = get<String>(qualifier = QualifierHistory.StoreName.qualifier),
            executor = get<Executor>(),
            bootstrapper = get<Bootstrapper>(),
            reducer = get<Reducer>(),
        )
    }
    factory<String>(qualifier = QualifierHistory.StoreName.qualifier) {
        QualifierHistory.StoreName.name
    }
    factory<Executor> {
        Executor(
            historyRepository = get<HistoryRepository>()
        )
    }
    factoryOf(::Bootstrapper)
    factoryOf(::Reducer)
}