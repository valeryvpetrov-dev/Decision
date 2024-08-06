package dev.valeryvpetrov.decision.feature.make_decision.presentation.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import dev.valeryvpetrov.decision.base.presentation.ComponentWithStore
import dev.valeryvpetrov.decision.feature.decision.presentation.component.DecisionComponent
import dev.valeryvpetrov.decision.feature.make_decision.presentation.mvi.Intent
import dev.valeryvpetrov.decision.feature.make_decision.presentation.mvi.Label
import dev.valeryvpetrov.decision.feature.make_decision.presentation.mvi.State
import dev.valeryvpetrov.decision.feature.problem.api.Problem
import dev.valeryvpetrov.decision.feature.problem.presentation.component.ProblemComponent
import dev.valeryvpetrov.decision.feature.solution.api.Solution
import dev.valeryvpetrov.decision.feature.solution.presentation.component.SolutionComponent

typealias MakeDecisionComponent = Component

abstract class Component(
    componentContext: ComponentContext,
) : ComponentWithStore<State, Intent, Label>(
    componentContext = componentContext,
) {

    abstract fun onGoToSolution(solutions: List<Solution>?)
    abstract fun onGoToDecision(decisionMessage: String)
    abstract fun onBackToProblem(problem: Problem?)
    abstract fun onBackToSolution(solutions: List<Solution>?)
    abstract fun onRestart(problem: Problem?)

    abstract val childStack: Value<ChildStack<*, Child>>

    sealed class Child {
        class Problem(val component: ProblemComponent) : Child()
        class Solutions(val component: SolutionComponent) : Child()
        class Decision(val component: DecisionComponent) : Child()
    }

    interface Factory {
        fun create(componentContext: ComponentContext): Component
    }
}