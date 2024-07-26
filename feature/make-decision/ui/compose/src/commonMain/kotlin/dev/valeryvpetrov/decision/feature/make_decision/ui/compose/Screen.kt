package dev.valeryvpetrov.decision.feature.make_decision.ui.compose

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import dev.valeryvpetrov.decision.feature.make_decision.api.Component
import dev.valeryvpetrov.decision.feature.make_decision.api.Label
import kotlinx.coroutines.flow.collectLatest
import dev.valeryvpetrov.decision.feature.decision.ui.compose.Screen as DecisionScreen
import dev.valeryvpetrov.decision.feature.problem.ui.compose.Screen as ProblemScreen
import dev.valeryvpetrov.decision.feature.solution.ui.compose.Screen as SolutionScreen

@Composable
fun Screen(
    component: Component,
) {
    LaunchedEffect(Unit) {
        component.labels.collectLatest { label ->
            when (label) {
                is Label.GoToSolution -> component.onGoToSolution(label.solutions)
                is Label.GoToDecision -> component.onGoToDecision(label.decisionMessage)
                is Label.BackToProblem -> component.onBackToProblem(label.problem)
                is Label.BackToSolution -> component.onBackToSolution(label.solutions)
                is Label.Restart -> component.onRestart(label.problem)
            }
        }
    }

    Children(
        stack = component.childStack,
        modifier = Modifier.fillMaxSize(),
        animation = stackAnimation(slide())
    ) {
        when (val instance = it.instance) {
            is Component.Child.Decision -> DecisionScreen(instance.component)
            is Component.Child.Problem -> ProblemScreen(instance.component)
            is Component.Child.Solutions -> SolutionScreen(instance.component)
        }
    }
}
