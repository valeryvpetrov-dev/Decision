package decision.solutions.di

import com.arkivanov.decompose.ComponentContext
import decision.di.Qualifier
import decision.solutions.component.RealSolutionsComponent
import decision.solutions.component.SolutionsComponent
import org.koin.core.qualifier.named
import org.koin.dsl.module

internal val componentModule = module {
    factory<SolutionsComponent> { (componentContext: ComponentContext, onGoToProblem: () -> Unit, onGoToDecision: () -> Unit) ->
        RealSolutionsComponent(
            componentContext = componentContext,
            onGoToProblem = onGoToProblem,
            onGoToDecision = onGoToDecision,
            store = get(qualifier = named(Qualifier.MVI_STORE_SOLUTIONS))
        )
    }
}