package dev.valeryvpetrov.decision.feature.history.di

import org.koin.dsl.module

val historyFeatureModule = module {
    includes(
        componentModule,
        implModule,
        mviModule,
    )
}