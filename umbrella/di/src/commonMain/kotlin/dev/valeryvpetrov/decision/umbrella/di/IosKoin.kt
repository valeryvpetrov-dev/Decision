package dev.valeryvpetrov.decision.umbrella.di

import com.arkivanov.decompose.ComponentContext
import dev.valeryvpetrov.decision.feature.make_decision.api.Component
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

@Suppress("unused")
object IosKoin : KoinComponent {
    private val makeDecisionComponentFactory: Component.Factory by inject()

    fun createMakeDecisionComponent(componentContext: ComponentContext): Component =
        makeDecisionComponentFactory.create(componentContext)
}