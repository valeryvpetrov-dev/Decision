package dev.valeryvpetrov.decision.feature.solution.di

import dev.valeryvpetrov.decision.base.api.Provider
import dev.valeryvpetrov.decision.base.di.Qualifier.Feature.Solution
import dev.valeryvpetrov.decision.feature.solution.presentation.component.RealComponent
import dev.valeryvpetrov.decision.feature.solution.presentation.component.SolutionComponentFactory
import dev.valeryvpetrov.decision.feature.solution.presentation.mvi.StoreFactory
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