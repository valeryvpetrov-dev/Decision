package dev.valeryvpetrov.decision.feature.make_decision.di

import org.koin.dsl.module
import dev.valeryvpetrov.decision.feature.decision.di.featureModule as decisionFeatureModule
import dev.valeryvpetrov.decision.feature.problem.di.featureModule as problemFeatureModule
import dev.valeryvpetrov.decision.feature.solution.di.featureModule as solutionFeatureModule

val makeDecisionFeatureModule = module {
    includes(
        componentModule,
        mviModule,
        repositoryModule,
        problemFeatureModule,
        solutionFeatureModule,
        decisionFeatureModule,
    )
}