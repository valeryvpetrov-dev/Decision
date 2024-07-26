package dev.valeryvpetrov.decision.feature.make_decision.presentation.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.router.stack.replaceAll
import com.arkivanov.decompose.value.Value
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import dev.valeryvpetrov.decision.base.api.Provider
import dev.valeryvpetrov.decision.feature.decision.presentation.component.DecisionComponentFactory
import dev.valeryvpetrov.decision.feature.make_decision.presentation.mvi.Intent
import dev.valeryvpetrov.decision.feature.make_decision.presentation.mvi.Label
import dev.valeryvpetrov.decision.feature.make_decision.presentation.mvi.State
import dev.valeryvpetrov.decision.feature.make_decision.presentation.mvi.StoreFactory
import dev.valeryvpetrov.decision.feature.problem.api.Problem
import dev.valeryvpetrov.decision.feature.problem.presentation.component.ProblemComponentFactory
import dev.valeryvpetrov.decision.feature.solution.api.Solution
import dev.valeryvpetrov.decision.feature.solution.presentation.component.SolutionComponentFactory
import kotlinx.coroutines.flow.Flow

class RealComponent(
    componentContext: ComponentContext,
    private val decisionComponentFactory: DecisionComponentFactory,
    private val solutionComponentFactory: SolutionComponentFactory,
    private val problemComponentFactory: ProblemComponentFactory,
    storeFactoryProvider: Provider<StoreFactory>,
) : ComponentContext by componentContext, MakeDecisionComponent {

    class Factory(
        private val decisionComponentFactory: DecisionComponentFactory,
        private val solutionComponentFactory: SolutionComponentFactory,
        private val problemComponentFactory: ProblemComponentFactory,
        private val storeFactoryProvider: Provider<StoreFactory>,
    ) : Component.Factory {

        override fun create(
            componentContext: ComponentContext,
        ): Component = RealComponent(
            componentContext = componentContext,
            decisionComponentFactory = decisionComponentFactory,
            solutionComponentFactory = solutionComponentFactory,
            problemComponentFactory = problemComponentFactory,
            storeFactoryProvider = storeFactoryProvider,
        )
    }

    private val store: Store<Intent, State, Label> = instanceKeeper.getStore {
        storeFactoryProvider.get().create(stateKeeper)
    }

    private var navigation = StackNavigation<Config>()

    override val labels: Flow<Label> = store.labels

    override var childStack: Value<ChildStack<*, Component.Child>> = childStack(
        source = navigation,
        serializer = Config.serializer(),
        initialConfiguration = Config.Problem(Problem.empty()),
        handleBackButton = true,
        childFactory = ::createChild
    )

    override fun onGoToSolution(solutions: List<Solution>?) {
        navigation.push(Config.Solution(solutions))
    }

    override fun onGoToDecision(decisionMessage: String) {
        navigation.push(Config.Decision(decisionMessage))
    }

    override fun onBackToProblem(problem: Problem?) {
        navigation.pop()
    }

    // FIXME: back after process death
    override fun onBackToSolution(solutions: List<Solution>?) {
        navigation.pop()
    }

    override fun onRestart(problem: Problem?) {
        navigation.replaceAll(Config.Problem(problem))
    }

    private fun createChild(
        config: Config,
        componentContext: ComponentContext,
    ): Component.Child = when (config) {
        is Config.Decision -> {
            val component = decisionComponentFactory.create(
                componentContext = componentContext,
                decisionMessage = config.decisionMessage,
                onGoToSolution = {
                    store.accept(Intent.BackToSolution)
                },
                onRestart = {
                    store.accept(Intent.Restart)
                },
            )
            Component.Child.Decision(component)
        }

        is Config.Problem -> {
            val component = problemComponentFactory.create(
                componentContext = componentContext,
                problem = config.problem,
                onGoToSolutions = { problem ->
                    store.accept(Intent.GoToSolution(problem))
                },
            )
            Component.Child.Problem(component)
        }

        is Config.Solution -> {
            val component = solutionComponentFactory.create(
                componentContext = componentContext,
                solutions = config.solutions,
                onBackToProblem = { solutions ->
                    store.accept(Intent.BackToProblem(solutions))
                },
                onGoToDecision = { solutions ->
                    store.accept(Intent.GoToDecision(solutions))
                },
            )
            Component.Child.Solutions(component)
        }
    }
}