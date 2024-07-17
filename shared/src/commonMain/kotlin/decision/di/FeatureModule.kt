package decision.di

import org.koin.dsl.module
import decision.decision.di.featureModule as decisionFeatureModule
import decision.problem.di.featureModule as problemFeatureModule
import decision.solutions.di.featureModule as solutionsFeatureModule

val featureModule = module {
    includes(
        componentModule,
        mviModule,
        repositoryModule,
        problemFeatureModule,
        solutionsFeatureModule,
        decisionFeatureModule,
    )
}