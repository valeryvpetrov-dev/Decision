package dev.valeryvpetrov.decision.feature.make_decision.presentation.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.popTo
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.router.stack.replaceAll
import com.arkivanov.decompose.value.Value
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.core.store.Store
import dev.valeryvpetrov.decision.base.api.Provider
import dev.valeryvpetrov.decision.feature.decision.presentation.component.DecisionComponent
import dev.valeryvpetrov.decision.feature.make_decision.presentation.mvi.MakeDecisionIntent
import dev.valeryvpetrov.decision.feature.make_decision.presentation.mvi.MakeDecisionState
import dev.valeryvpetrov.decision.feature.make_decision.presentation.mvi.StoreFactory
import dev.valeryvpetrov.decision.feature.problem.api.Problem
import dev.valeryvpetrov.decision.feature.problem.presentation.component.ProblemComponent
import dev.valeryvpetrov.decision.feature.solution.api.Solution
import dev.valeryvpetrov.decision.feature.solution.presentation.component.SolutionComponent

class DefaultMakeDecisionComponent(
    componentContext: ComponentContext,
    private val decisionComponentFactory: DecisionComponent.Factory,
    private val solutionComponentFactory: SolutionComponent.Factory,
    private val problemComponentFactory: ProblemComponent.Factory,
    storeFactoryProvider: Provider<StoreFactory>,
) : ComponentContext by componentContext, MakeDecisionComponent(
    componentContext = componentContext,
) {

    class Factory(
        private val decisionComponentFactory: DecisionComponent.Factory,
        private val solutionComponentFactory: SolutionComponent.Factory,
        private val problemComponentFactory: ProblemComponent.Factory,
        private val storeFactoryProvider: Provider<StoreFactory>,
    ) : MakeDecisionComponent.Factory {

        override fun create(
            componentContext: ComponentContext,
        ): MakeDecisionComponent = DefaultMakeDecisionComponent(
            componentContext = componentContext,
            decisionComponentFactory = decisionComponentFactory,
            solutionComponentFactory = solutionComponentFactory,
            problemComponentFactory = problemComponentFactory,
            storeFactoryProvider = storeFactoryProvider,
        )
    }

    override val store: Store<MakeDecisionIntent, MakeDecisionState, Nothing> =
        instanceKeeper.getStore {
            storeFactoryProvider.get().create(
                stateKeeper = stateKeeper,
                onGoToSolution = ::onGoToSolution,
                onGoToDecision = ::onGoToDecision,
                onBackToProblem = ::onBackToProblem,
                onRestart = ::onRestart,
                onBackToSolution = ::onBackToSolution,
            )
    }

    private var navigation = StackNavigation<Config>()

    override var childStack: Value<ChildStack<*, Child>> = childStack(
        source = navigation,
        serializer = Config.serializer(),
        initialConfiguration = Config.Problem(Problem.empty()),
        handleBackButton = true,
        childFactory = ::createChild
    )

    // FIXME: handle saving state. For example, on data change
    override fun onBack() {
        navigation.pop()
    }

    // FIXME: handle saving state. For example, on data change
    override fun onBack(toIndex: Int) {
        navigation.popTo(index = toIndex)
    }

    private fun onGoToSolution(solutions: List<Solution>?) {
        navigation.push(Config.Solution(solutions))
    }

    private fun onGoToDecision(decisionMessage: String) {
        navigation.push(Config.Decision(decisionMessage))
    }

    private fun onBackToProblem(problem: Problem?) {
        navigation.pop()
    }

    private fun onBackToSolution(solutions: List<Solution>?) {
        navigation.pop()
    }

    private fun onRestart(problem: Problem?) {
        navigation.replaceAll(Config.Problem(problem))
    }

    private fun createChild(
        config: Config,
        componentContext: ComponentContext,
    ): Child = when (config) {
        is Config.Decision -> {
            val component = decisionComponentFactory.create(
                componentContext = componentContext,
                decisionMessage = config.decisionMessage,
                onGoToSolution = {
                    store.accept(MakeDecisionIntent.BackToSolution)
                },
                onRestart = {
                    store.accept(MakeDecisionIntent.Restart)
                },
            )
            Child.Decision(component)
        }

        is Config.Problem -> {
            val component = problemComponentFactory.create(
                componentContext = componentContext,
                problem = config.problem,
                onGoToSolutions = { problem ->
                    store.accept(MakeDecisionIntent.GoToSolution(problem))
                },
            )
            Child.Problem(component)
        }

        is Config.Solution -> {
            val component = solutionComponentFactory.create(
                componentContext = componentContext,
                solutions = config.solutions,
                onBackToProblem = { solutions ->
                    store.accept(MakeDecisionIntent.BackToProblem(solutions))
                },
                onGoToDecision = { solutions ->
                    store.accept(MakeDecisionIntent.GoToDecision(solutions))
                },
            )
            Child.Solutions(component)
        }
    }
}