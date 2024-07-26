package dev.valeryvpetrov.decision.feature.decision.di

import dev.valeryvpetrov.decision.base.api.Provider
import dev.valeryvpetrov.decision.base.di.Qualifier
import dev.valeryvpetrov.decision.feature.decision.presentation.component.DecisionComponentFactory
import dev.valeryvpetrov.decision.feature.decision.presentation.component.RealComponent
import dev.valeryvpetrov.decision.feature.decision.presentation.mvi.StoreFactory
import org.koin.dsl.module

internal val componentModule = module {
    factory<DecisionComponentFactory> {
        RealComponent.Factory(
            storeFactoryProvider = get<Provider<StoreFactory>>(
                qualifier = Qualifier.Feature.Decision.StoreFactoryProvider.qualifier
            ),
        )
    }
}