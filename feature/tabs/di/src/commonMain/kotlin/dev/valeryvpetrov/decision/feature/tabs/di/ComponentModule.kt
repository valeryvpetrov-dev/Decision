package dev.valeryvpetrov.decision.feature.tabs.di

import dev.valeryvpetrov.decision.feature.history.presentation.component.HistoryComponent
import dev.valeryvpetrov.decision.feature.make_decision.presentation.component.MakeDecisionComponent
import dev.valeryvpetrov.decision.feature.tabs.presentation.component.DefaultTabsComponent
import dev.valeryvpetrov.decision.feature.tabs.presentation.component.TabsComponent
import org.koin.dsl.module

val tabsComponentModule = module {
    factory<TabsComponent.Factory> {
        DefaultTabsComponent.Factory(
            makeDecisionComponentFactory = get<MakeDecisionComponent.Factory>(),
            historyComponentFactory = get<HistoryComponent.Factory>()
        )
    }
}