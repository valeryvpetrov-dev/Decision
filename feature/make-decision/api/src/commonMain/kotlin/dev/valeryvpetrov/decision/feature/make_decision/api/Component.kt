package dev.valeryvpetrov.decision.feature.make_decision.api

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import dev.valeryvpetrov.decision.feature.decision.api.DecisionComponent
import dev.valeryvpetrov.decision.feature.problem.api.ProblemComponent
import dev.valeryvpetrov.decision.feature.solution.api.SolutionComponent

typealias MakeDecisionComponent = Component
typealias MakeDecisionComponentFactory = Component.Factory

interface Component {

    val childStack: Value<ChildStack<*, Child>>

    sealed class Child {
        class Problem(val component: ProblemComponent) : Child()
        class Solutions(val component: SolutionComponent) : Child()
        class Decision(val component: DecisionComponent) : Child()
    }

    interface Factory {
        fun create(componentContext: ComponentContext): Component
    }
}