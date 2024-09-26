package dev.valeryvpetrov.decision.feature.history.di

import dev.valeryvpetrov.decision.base.api.Provider
import dev.valeryvpetrov.decision.base.di.Qualifier.Feature.History
import dev.valeryvpetrov.decision.feature.history.presentation.component.DefaultHistoryComponent
import dev.valeryvpetrov.decision.feature.history.presentation.component.HistoryComponent
import dev.valeryvpetrov.decision.feature.history.presentation.mvi.StoreFactory
import org.koin.dsl.module

val componentModule = module {
    single<HistoryComponent.Factory> {
        DefaultHistoryComponent.Factory(
            storeFactoryProvider = get<Provider<StoreFactory>>(
                qualifier = History.StoreFactoryProvider.qualifier
            )
        )
    }
}