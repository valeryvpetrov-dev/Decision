package dev.valeryvpetrov.decision.umbrella.di

import com.arkivanov.decompose.ComponentContext
import dev.valeryvpetrov.decision.feature.make_decision.presentation.component.MakeDecisionComponent
import dev.valeryvpetrov.decision.feature.problem.presentation.component.ProblemComponent
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

@Suppress("unused")
object IosKoin : KoinComponent {
    private val makeDecisionComponentFactory: MakeDecisionComponent.Factory by inject()
    private val problemComponentFactory: ProblemComponent.Factory by inject()

    fun createMakeDecisionComponent(componentContext: ComponentContext): MakeDecisionComponent =
        makeDecisionComponentFactory.create(componentContext)
}