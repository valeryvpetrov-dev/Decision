package dev.valeryvpetrov.decision.feature.decision.di

import org.koin.dsl.module

val featureModule = module {
    includes(
        componentModule,
        mviModule
    )
}