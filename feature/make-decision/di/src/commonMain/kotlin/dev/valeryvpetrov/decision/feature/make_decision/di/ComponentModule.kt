package dev.valeryvpetrov.decision.feature.make_decision.di

import dev.valeryvpetrov.decision.base.api.Provider
import dev.valeryvpetrov.decision.base.di.Qualifier
import dev.valeryvpetrov.decision.feature.decision.presentation.component.DecisionComponent
import dev.valeryvpetrov.decision.feature.make_decision.presentation.component.DefaultMakeDecisionComponent
import dev.valeryvpetrov.decision.feature.make_decision.presentation.component.MakeDecisionComponent
import dev.valeryvpetrov.decision.feature.make_decision.presentation.mvi.StoreFactory
import dev.valeryvpetrov.decision.feature.problem.presentation.component.ProblemComponent
import dev.valeryvpetrov.decision.feature.solution.presentation.component.SolutionComponent
import org.koin.dsl.module

val componentModule = module {
    factory<MakeDecisionComponent.Factory> {
        DefaultMakeDecisionComponent.Factory(
            decisionComponentFactory = get<DecisionComponent.Factory>(),
            solutionComponentFactory = get<SolutionComponent.Factory>(),
            problemComponentFactory = get<ProblemComponent.Factory>(),
            storeFactoryProvider = get<Provider<StoreFactory>>(
                qualifier = Qualifier.Feature.MakeDecision.StoreFactoryProvider.qualifier
            ),
        )
    }
}