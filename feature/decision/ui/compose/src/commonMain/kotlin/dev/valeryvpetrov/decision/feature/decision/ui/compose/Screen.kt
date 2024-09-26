package dev.valeryvpetrov.decision.feature.decision.ui.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.valeryvpetrov.decision.feature.decision.presentation.component.DecisionComponent
import dev.valeryvpetrov.decision.feature.decision.presentation.mvi.DecisionIntent
import dev.valeryvpetrov.decision.feature.decision.presentation.mvi.DecisionState

@Composable
fun Screen(
    component: DecisionComponent,
) {
    val state by component.state.collectAsState()
    ScreenContent(
        state = state,
        onGoToSolutions = {
            component.accept(DecisionIntent.GoToSolutions)
        },
        onRestart = {
            component.accept(DecisionIntent.Restart)
        }
    )
}

@Composable
private fun ScreenContent(
    state: DecisionState,
    onGoToSolutions: () -> Unit,
    onRestart: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = state.decisionMessage,
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                modifier = Modifier
                    .weight(1f),
                onClick = onGoToSolutions,
                enabled = state.isGoToSolutionsEnabled,
            ) {
                Text("To solutions")
            }
            Button(
                modifier = Modifier
                    .weight(1f),
                onClick = onRestart,
                enabled = state.isRestartEnabled,
            ) {
                Text("Restart")
            }
        }
    }
}
