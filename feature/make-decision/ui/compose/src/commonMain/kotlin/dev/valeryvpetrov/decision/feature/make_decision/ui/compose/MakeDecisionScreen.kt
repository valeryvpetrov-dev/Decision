package dev.valeryvpetrov.decision.feature.make_decision.ui.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import dev.valeryvpetrov.decision.feature.make_decision.presentation.component.MakeDecisionComponent
import dev.valeryvpetrov.decision.feature.decision.ui.compose.Screen as DecisionScreen
import dev.valeryvpetrov.decision.feature.problem.ui.compose.Screen as ProblemScreen
import dev.valeryvpetrov.decision.feature.solution.ui.compose.Screen as SolutionScreen

@Composable
fun MakeDecisionScreen(
    component: MakeDecisionComponent,
    modifier: Modifier = Modifier,
) {
    Children(
        stack = component.childStack,
        modifier = modifier,
        animation = stackAnimation(slide())
    ) {
        when (val instance = it.instance) {
            is MakeDecisionComponent.Child.Decision -> DecisionScreen(instance.component)
            is MakeDecisionComponent.Child.Problem -> ProblemScreen(instance.component)
            is MakeDecisionComponent.Child.Solutions -> SolutionScreen(instance.component)
        }
    }
}
