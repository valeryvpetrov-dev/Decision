package decision.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.router.stack.replaceAll
import com.arkivanov.decompose.value.Value
import decision.decision.component.DecisionComponent
import decision.problem.component.ProblemComponent
import decision.solutions.component.SolutionsComponent
import kotlinx.serialization.Serializable
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.parameter.parametersOf

class RealComponent(
    componentContext: ComponentContext,
) : ComponentContext by componentContext, Component, KoinComponent {

    @Serializable
    private sealed class Config {

        // Class is used to separate configs after make decision flow pass
        @Serializable
        class Problem : Config()

        @Serializable
        data object Solutions : Config()

        @Serializable
        data object Decision : Config()
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
            val component = get<DecisionComponent> {
                parametersOf(
                    componentContext,
                    { navigation.pop() },
                    { navigation.replaceAll(Config.Problem()) }
                )
            }
            Component.Child.Decision(component)
        }

        is Config.Problem -> {
            val component = get<ProblemComponent> {
                parametersOf(
                    componentContext,
                    { navigation.push(Config.Solutions) },
                )
            }
            Component.Child.Problem(component)
        }

        is Config.Solutions -> {
            val component = get<SolutionsComponent> {
                parametersOf(
                    componentContext,
                    { navigation.pop() },
                    { navigation.push(Config.Decision) },
                )
            }
            Component.Child.Solutions(component)
        }
    }
}