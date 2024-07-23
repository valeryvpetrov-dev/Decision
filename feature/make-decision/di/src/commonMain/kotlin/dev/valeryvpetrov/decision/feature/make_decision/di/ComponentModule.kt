package dev.valeryvpetrov.decision.feature.make_decision.di

import dev.valeryvpetrov.decision.base.api.Provider
import dev.valeryvpetrov.decision.base.di.Qualifier
import dev.valeryvpetrov.decision.feature.decision.api.DecisionComponentFactory
import dev.valeryvpetrov.decision.feature.make_decision.api.Component
import dev.valeryvpetrov.decision.feature.make_decision.impl.component.RealComponent
import dev.valeryvpetrov.decision.feature.make_decision.impl.mvi.StoreFactory
import dev.valeryvpetrov.decision.feature.problem.api.ProblemComponentFactory
import dev.valeryvpetrov.decision.feature.solution.api.SolutionComponentFactory
import org.koin.dsl.module

val componentModule = module {
    factory<Component.Factory> {
        RealComponent.Factory(
            decisionComponentFactory = get<DecisionComponentFactory>(),
            solutionComponentFactory = get<SolutionComponentFactory>(),
            problemComponentFactory = get<ProblemComponentFactory>(),
            storeFactoryProvider = get<Provider<StoreFactory>>(
                qualifier = Qualifier.Feature.MakeDecision.StoreFactoryProvider.qualifier
            ),
        )
    }
}