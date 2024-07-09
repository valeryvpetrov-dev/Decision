package decision.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.popToFirst
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import decision.decision.component.RealDecisionComponent
import decision.problem.component.RealProblemComponent
import decision.repository.DecisionRepository
import decision.repository.DecisionRepositoryImpl
import decision.solutions.component.RealSolutionsComponent
import kotlinx.serialization.Serializable

class RealComponent(
    componentContext: ComponentContext,
    // FIXME DI
    private val decisionRepository: DecisionRepository = DecisionRepositoryImpl()
) : ComponentContext by componentContext, Component {

    @Serializable
    private sealed class Config {

        @Serializable
        data object Problem : Config()

        @Serializable
        data object Solutions : Config()

        @Serializable
        data object Decision : Config()
    }

    private var navigation = StackNavigation<Config>()

    override var childStack: Value<ChildStack<*, Component.Child>> = childStack(
        source = navigation,
        serializer = Config.serializer(),
        initialConfiguration = Config.Problem,
        handleBackButton = true,
        childFactory = ::createChild
    )

    private fun createChild(
        config: Config,
        componentContext: ComponentContext
    ): Component.Child = when (config) {
        is Config.Decision -> Component.Child.Decision(
            RealDecisionComponent(
                componentContext = componentContext,
                onGoToSolutions = {
                    navigation.pop()
                },
                onRestart = {
                    navigation.popToFirst()
                },
                decisionRepository = decisionRepository,
            )
        )

        is Config.Problem -> Component.Child.Problem(
            RealProblemComponent(
                componentContext = componentContext,
                onGoToSolutions = {
                    navigation.push(Config.Solutions)
                },
                decisionRepository = decisionRepository,
            ),
        )

        is Config.Solutions -> Component.Child.Solutions(
            RealSolutionsComponent(
                componentContext = componentContext,
                onGoToProblem = {
                    navigation.pop()
                },
                onGoToDecision = {
                    navigation.push(Config.Decision)
                },
                decisionRepository = decisionRepository,
            )
        )
    }
}