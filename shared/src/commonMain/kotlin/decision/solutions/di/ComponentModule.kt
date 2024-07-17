package decision.solutions.di

import com.arkivanov.decompose.ComponentContext
import decision.solutions.component.RealSolutionsComponent
import decision.solutions.component.SolutionsComponent
import org.koin.dsl.module

internal val componentModule = module {
    factory<SolutionsComponent> { (componentContext: ComponentContext, onGoToProblem: () -> Unit, onGoToDecision: () -> Unit) ->
        RealSolutionsComponent(
            componentContext = componentContext,
            onGoToProblem = onGoToProblem,
            onGoToDecision = onGoToDecision,
            storeFactory = get()
        )
    }
}