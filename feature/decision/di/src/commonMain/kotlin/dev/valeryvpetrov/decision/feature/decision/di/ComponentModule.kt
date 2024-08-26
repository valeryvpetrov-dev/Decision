package dev.valeryvpetrov.decision.feature.decision.di

import dev.valeryvpetrov.decision.base.api.Provider
import dev.valeryvpetrov.decision.base.di.Qualifier
import dev.valeryvpetrov.decision.feature.decision.presentation.component.DecisionComponent
import dev.valeryvpetrov.decision.feature.decision.presentation.component.DefaultDecisionComponent
import dev.valeryvpetrov.decision.feature.decision.presentation.mvi.StoreFactory
import org.koin.dsl.module

internal val componentModule = module {
    factory<DecisionComponent.Factory> {
        DefaultDecisionComponent.Factory(
            storeFactoryProvider = get<Provider<StoreFactory>>(
                qualifier = Qualifier.Feature.Decision.StoreFactoryProvider.qualifier
            ),
        )
    }
}