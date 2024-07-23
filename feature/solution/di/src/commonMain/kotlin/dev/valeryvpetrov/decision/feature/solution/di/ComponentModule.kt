package dev.valeryvpetrov.decision.feature.solution.di

import dev.valeryvpetrov.decision.base.api.Provider
import dev.valeryvpetrov.decision.base.di.Qualifier.Feature.Solution
import dev.valeryvpetrov.decision.feature.solution.api.SolutionComponentFactory
import dev.valeryvpetrov.decision.feature.solution.impl.component.RealComponent
import dev.valeryvpetrov.decision.feature.solution.impl.mvi.StoreFactory
import org.koin.dsl.module

internal val componentModule = module {
    factory<SolutionComponentFactory> {
        RealComponent.Factory(
            storeFactoryProvider = get<Provider<StoreFactory>>(
                qualifier = Solution.StoreFactoryProvider.qualifier
            )
        )
    }
}