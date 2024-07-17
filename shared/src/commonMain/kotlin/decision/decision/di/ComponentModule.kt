package decision.decision.di

import com.arkivanov.decompose.ComponentContext
import decision.decision.component.DecisionComponent
import decision.decision.component.RealDecisionComponent
import org.koin.dsl.module

internal val componentModule = module {
    factory<DecisionComponent> { (componentContext: ComponentContext, onGoToSolutions: () -> Unit, onRestart: () -> Unit) ->
        RealDecisionComponent(
            componentContext = componentContext,
            onGoToSolutions = onGoToSolutions,
            onRestart = onRestart,
            storeFactory = get(),
        )
    }
}