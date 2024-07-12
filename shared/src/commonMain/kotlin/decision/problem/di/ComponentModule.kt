package decision.problem.di

import com.arkivanov.decompose.ComponentContext
import decision.di.Qualifier
import decision.problem.component.ProblemComponent
import decision.problem.component.RealProblemComponent
import org.koin.core.qualifier.named
import org.koin.dsl.module

internal val componentModule = module {
    factory<ProblemComponent> { (componentContext: ComponentContext, onGoToSolutions: () -> Unit) ->
        RealProblemComponent(
            componentContext = componentContext,
            onGoToSolutions = onGoToSolutions,
            store = get(qualifier = named(Qualifier.MVI_STORE_PROBLEM))
        )
    }
}