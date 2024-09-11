package dev.valeryvpetrov.decision.feature.tabs.ui.compose

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import dev.valeryvpetrov.decision.feature.make_decision.ui.compose.MakeDecisionScreen
import dev.valeryvpetrov.decision.feature.tabs.presentation.component.TabsComponent
import dev.valeryvpetrov.decision.feature.tabs.presentation.component.TabsComponent.Child

@Composable
fun TabsScreen(
    component: TabsComponent,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        bottomBar = {
            BottomNavigationBar(component = component, modifier = modifier)
        }
    ) {
        Children(
            stack = component.tabs,
            modifier = modifier,
            animation = stackAnimation(slide())
        ) {
            when (val child = it.instance) {
                is Child.History -> Text("""TODO("HistoryScreen(component = child.component, modifier = Modifier.fillMaxSize())")""")
                is Child.MakeDecision -> MakeDecisionScreen(
                    component = child.component,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}

@Composable
private fun BottomNavigationBar(component: TabsComponent, modifier: Modifier = Modifier) {
    val stack by component.tabs.subscribeAsState()
    val activeTab = stack.active.instance
    BottomNavigation(
        modifier = modifier
            .fillMaxWidth()
            .navigationBarsPadding(),
    ) {
        BottomNavigationItem(
            selected = activeTab is Child.MakeDecision,
            onClick = component::onMakeDecisionItemClicked,
            icon = {
                Icon(
                    imageVector = Icons.Default.Done,
                    contentDescription = "Make decision",
                )
            },
        )
        BottomNavigationItem(
            selected = activeTab is Child.History,
            onClick = component::onHistoryItemClicked,
            icon = {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.List,
                    contentDescription = "History",
                )
            },
        )
    }
}