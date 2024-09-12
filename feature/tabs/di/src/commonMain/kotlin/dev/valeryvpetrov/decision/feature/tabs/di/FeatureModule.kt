package dev.valeryvpetrov.decision.feature.tabs.di

import dev.valeryvpetrov.decision.feature.history.di.historyFeatureModule
import dev.valeryvpetrov.decision.feature.make_decision.di.makeDecisionFeatureModule
import org.koin.dsl.module

val tabsFeatureModule = module {
    includes(
        tabsComponentModule,
        makeDecisionFeatureModule,
        historyFeatureModule,
    )
}