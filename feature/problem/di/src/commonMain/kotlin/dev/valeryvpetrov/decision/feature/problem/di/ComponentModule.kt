package dev.valeryvpetrov.decision.feature.problem.di

import dev.valeryvpetrov.decision.base.api.Provider
import dev.valeryvpetrov.decision.base.di.Qualifier
import dev.valeryvpetrov.decision.feature.problem.presentation.component.DefaultProblemComponent
import dev.valeryvpetrov.decision.feature.problem.presentation.component.ProblemComponent
import dev.valeryvpetrov.decision.feature.problem.presentation.mvi.StoreFactory
import org.koin.dsl.module

internal val componentModule = module {
    factory<ProblemComponent.Factory> {
        DefaultProblemComponent.Factory(
            storeFactoryProvider = get<Provider<StoreFactory>>(
                qualifier = Qualifier.Feature.Problem.StoreFactoryProvider.qualifier
            )
        )
    }
}