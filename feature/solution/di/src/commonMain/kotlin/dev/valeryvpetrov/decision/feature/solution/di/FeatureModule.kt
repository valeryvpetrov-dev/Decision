package dev.valeryvpetrov.decision.feature.solution.di

import org.koin.dsl.module

val featureModule = module {
    includes(
        componentModule,
        mviModule
    )
}