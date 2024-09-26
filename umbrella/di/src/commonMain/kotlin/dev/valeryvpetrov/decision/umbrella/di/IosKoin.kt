package dev.valeryvpetrov.decision.umbrella.di

import com.arkivanov.decompose.ComponentContext
import dev.valeryvpetrov.decision.feature.tabs.presentation.component.TabsComponent
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

@Suppress("unused")
object IosKoin : KoinComponent {
    private val tabsComponentFactory: TabsComponent.Factory by inject()

    fun createTabsComponent(componentContext: ComponentContext): TabsComponent =
        tabsComponentFactory.create(componentContext)
}