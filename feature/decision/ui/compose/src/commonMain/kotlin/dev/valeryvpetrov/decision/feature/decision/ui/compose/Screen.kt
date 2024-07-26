package dev.valeryvpetrov.decision.feature.decision.ui.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.valeryvpetrov.decision.feature.decision.api.DecisionComponent
import dev.valeryvpetrov.decision.feature.decision.api.Intent
import dev.valeryvpetrov.decision.feature.decision.api.State

@Composable
fun Screen(
    component: DecisionComponent,
) {
    val state by component.state.collectAsState()
    ScreenContent(
        state = state,
        onGoToSolutions = {
            component.accept(Intent.GoToSolutions)
        },
        onRestart = {
            component.accept(Intent.Restart)
        }
    )
}

@Composable
private fun ScreenContent(
    state: State,
    onGoToSolutions: () -> Unit,
    onRestart: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
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
