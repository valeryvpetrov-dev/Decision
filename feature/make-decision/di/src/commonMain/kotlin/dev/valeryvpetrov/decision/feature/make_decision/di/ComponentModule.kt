package dev.valeryvpetrov.decision.feature.make_decision.di

import dev.valeryvpetrov.decision.base.api.Provider
import dev.valeryvpetrov.decision.base.di.Qualifier
import dev.valeryvpetrov.decision.feature.decision.presentation.component.DecisionComponentFactory
import dev.valeryvpetrov.decision.feature.make_decision.presentation.component.Component
import dev.valeryvpetrov.decision.feature.make_decision.presentation.component.RealComponent
import dev.valeryvpetrov.decision.feature.make_decision.presentation.mvi.StoreFactory
import dev.valeryvpetrov.decision.feature.problem.presentation.component.ProblemComponentFactory
import dev.valeryvpetrov.decision.feature.solution.presentation.component.SolutionComponentFactory
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