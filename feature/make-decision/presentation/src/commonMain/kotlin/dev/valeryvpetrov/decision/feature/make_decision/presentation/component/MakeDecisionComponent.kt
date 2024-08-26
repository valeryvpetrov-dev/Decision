package dev.valeryvpetrov.decision.feature.make_decision.presentation.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import dev.valeryvpetrov.decision.base.presentation.ComponentWithStore
import dev.valeryvpetrov.decision.feature.decision.presentation.component.DecisionComponent
import dev.valeryvpetrov.decision.feature.make_decision.presentation.mvi.MakeDecisionIntent
import dev.valeryvpetrov.decision.feature.make_decision.presentation.mvi.MakeDecisionState
import dev.valeryvpetrov.decision.feature.problem.presentation.component.ProblemComponent
import dev.valeryvpetrov.decision.feature.solution.presentation.component.SolutionComponent

abstract class MakeDecisionComponent(
    componentContext: ComponentContext,
) : ComponentWithStore<MakeDecisionState, MakeDecisionIntent, Nothing>(
    componentContext = componentContext,
) {

    abstract fun onBack()
    abstract fun onBack(toIndex: Int)

    abstract val childStack: Value<ChildStack<*, Child>>

    sealed class Child {
        class Problem(val component: ProblemComponent) : Child()
        class Solutions(val component: SolutionComponent) : Child()
        class Decision(val component: DecisionComponent) : Child()
    }

    interface Factory {
        fun create(componentContext: ComponentContext): MakeDecisionComponent
    }
}