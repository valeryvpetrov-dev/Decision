package dev.valeryvpetrov.decision.feature.make_decision.api

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import dev.valeryvpetrov.decision.domain.Problem
import dev.valeryvpetrov.decision.domain.Solution
import dev.valeryvpetrov.decision.feature.decision.api.DecisionComponent
import dev.valeryvpetrov.decision.feature.problem.api.ProblemComponent
import dev.valeryvpetrov.decision.feature.solution.api.SolutionComponent
import kotlinx.coroutines.flow.Flow

typealias MakeDecisionComponent = Component

interface Component {

    val labels: Flow<Label>

    fun onGoToSolution(solutions: List<Solution>?)
    fun onGoToDecision(decisionMessage: String)
    fun onBackToProblem(problem: Problem?)
    fun onBackToSolution(solutions: List<Solution>?)
    fun onRestart(problem: Problem?)

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