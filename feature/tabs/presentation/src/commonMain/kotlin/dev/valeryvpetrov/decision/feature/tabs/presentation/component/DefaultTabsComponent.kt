package dev.valeryvpetrov.decision.feature.tabs.presentation.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import dev.valeryvpetrov.decision.feature.history.presentation.component.HistoryComponent
import dev.valeryvpetrov.decision.feature.make_decision.presentation.component.MakeDecisionComponent
import dev.valeryvpetrov.decision.feature.tabs.presentation.component.TabsComponent.Child

class DefaultTabsComponent(
    componentContext: ComponentContext,
    private val makeDecisionComponentFactory: MakeDecisionComponent.Factory,
    private val historyComponentFactory: HistoryComponent.Factory,
) : ComponentContext by componentContext, TabsComponent {

    class Factory(
        private val makeDecisionComponentFactory: MakeDecisionComponent.Factory,
        private val historyComponentFactory: HistoryComponent.Factory,
    ) : TabsComponent.Factory {

        override fun create(componentContext: ComponentContext): TabsComponent =
            DefaultTabsComponent(
                componentContext = componentContext,
                makeDecisionComponentFactory = makeDecisionComponentFactory,
                historyComponentFactory = historyComponentFactory,
            )
    }

    private var navigation = StackNavigation<Config>()

    override val tabs: Value<ChildStack<*, Child>> = childStack(
        source = navigation,
        serializer = Config.serializer(),
        initialConfiguration = Config.MakeDecision,
        handleBackButton = true,
        childFactory = ::createChild
    )

    override fun onMakeDecisionItemClicked() {
        navigation.bringToFront(Config.MakeDecision)
    }

    override fun onHistoryItemClicked() {
        navigation.bringToFront(Config.History)
    }

    private fun createChild(
        config: Config,
        componentContext: ComponentContext,
    ): Child = when (config) {
        is Config.History -> {
            val component = historyComponentFactory.create(
                componentContext = componentContext,
            )
            Child.History(component)
        }
        is Config.MakeDecision -> {
            val component = makeDecisionComponentFactory.create(
                componentContext = componentContext
            )
            Child.MakeDecision(component)
        }
    }
}