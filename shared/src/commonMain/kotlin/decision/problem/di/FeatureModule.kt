package decision.problem.di

import org.koin.dsl.module

internal val featureModule = module {
    includes(
        componentModule,
        mviModule
    )
}