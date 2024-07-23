package dev.valeryvpetrov.decision.feature.make_decision.impl.component

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
import dev.valeryvpetrov.decision.base.api.Provider
import dev.valeryvpetrov.decision.feature.decision.api.DecisionComponentFactory
import dev.valeryvpetrov.decision.feature.make_decision.api.Component
import dev.valeryvpetrov.decision.feature.make_decision.api.MakeDecisionComponent
import dev.valeryvpetrov.decision.feature.make_decision.api.State
import dev.valeryvpetrov.decision.feature.make_decision.impl.mvi.StoreFactory
import dev.valeryvpetrov.decision.feature.problem.api.ProblemComponentFactory
import dev.valeryvpetrov.decision.feature.solution.api.SolutionComponentFactory
import kotlinx.serialization.Serializable

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

    @Serializable
    private sealed class Config {

        // Class is used to separate configs after make decision flow pass. New instance - new config
        @Serializable
        class Problem : Config()

        @Serializable
        data object Solutions : Config()

        @Serializable
        data object Decision : Config()
    }

    private val store: Store<Nothing, State, Nothing> = instanceKeeper.getStore {
        storeFactoryProvider.get().create(stateKeeper)
    }

    private var navigation = StackNavigation<Config>()

    override var childStack: Value<ChildStack<*, Component.Child>> = childStack(
        source = navigation,
        serializer = Config.serializer(),
        initialConfiguration = Config.Problem(),
        handleBackButton = true,
        childFactory = ::createChild
    )

    private fun createChild(
        config: Config,
        componentContext: ComponentContext
    ): Component.Child = when (config) {
        is Config.Decision -> {
            val component = decisionComponentFactory.create(
                componentContext = componentContext,
                onGoToSolutions = { navigation.pop() },
                onRestart = { navigation.replaceAll(Config.Problem()) },
            )
            Component.Child.Decision(component)
        }

        is Config.Problem -> {
            val component = problemComponentFactory.create(
                componentContext = componentContext,
                onGoToSolutions = { navigation.push(Config.Solutions) },
            )
            Component.Child.Problem(component)
        }

        is Config.Solutions -> {
            val component = solutionComponentFactory.create(
                componentContext = componentContext,
                onGoToProblem = { navigation.pop() },
                onGoToDecision = { navigation.push(Config.Decision) },
            )
            Component.Child.Solutions(component)
        }
    }
}