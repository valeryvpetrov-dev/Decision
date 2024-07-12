package decision.decision.di

import com.arkivanov.decompose.ComponentContext
import decision.decision.component.DecisionComponent
import decision.decision.component.RealDecisionComponent
import decision.di.Qualifier
import org.koin.core.qualifier.named
import org.koin.dsl.module

internal val componentModule = module {
    factory<DecisionComponent> { (componentContext: ComponentContext, onGoToSolutions: () -> Unit, onRestart: () -> Unit) ->
        RealDecisionComponent(
            componentContext = componentContext,
            onGoToSolutions = onGoToSolutions,
            onRestart = onRestart,
            store = get(qualifier = named(Qualifier.MVI_STORE_DECISION))
        )
    }
}