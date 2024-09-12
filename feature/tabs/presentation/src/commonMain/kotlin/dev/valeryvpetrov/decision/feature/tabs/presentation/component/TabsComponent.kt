package dev.valeryvpetrov.decision.feature.tabs.presentation.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import dev.valeryvpetrov.decision.feature.history.presentation.component.HistoryComponent
import dev.valeryvpetrov.decision.feature.make_decision.presentation.component.MakeDecisionComponent

interface TabsComponent {

    sealed class Child {
        class MakeDecision(val component: MakeDecisionComponent) : Child()

        class History(val component: HistoryComponent) : Child()
    }

    interface Factory {
        fun create(componentContext: ComponentContext): TabsComponent
    }

    val tabs: Value<ChildStack<*, Child>>

    fun onMakeDecisionItemClicked()
    fun onHistoryItemClicked()
}