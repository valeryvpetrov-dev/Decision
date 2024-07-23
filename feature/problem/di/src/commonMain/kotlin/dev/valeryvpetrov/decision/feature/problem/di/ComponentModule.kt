package dev.valeryvpetrov.decision.feature.problem.di

import dev.valeryvpetrov.decision.base.api.Provider
import dev.valeryvpetrov.decision.base.di.Qualifier
import dev.valeryvpetrov.decision.feature.problem.api.ProblemComponentFactory
import dev.valeryvpetrov.decision.feature.problem.impl.component.RealComponent
import dev.valeryvpetrov.decision.feature.problem.impl.mvi.StoreFactory
import org.koin.dsl.module

internal val componentModule = module {
    factory<ProblemComponentFactory> {
        RealComponent.Factory(
            storeFactoryProvider = get<Provider<StoreFactory>>(
                qualifier = Qualifier.Feature.Problem.StoreFactoryProvider.qualifier
            )
        )
    }
}