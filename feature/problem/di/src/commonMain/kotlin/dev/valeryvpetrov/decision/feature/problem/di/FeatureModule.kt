package dev.valeryvpetrov.decision.feature.problem.di

import org.koin.dsl.module

val featureModule = module {
    includes(
        componentModule,
        mviModule
    )
}