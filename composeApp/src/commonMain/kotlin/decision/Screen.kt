package decision

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import decision.decision.component.Component
import decision.decision.mvi.Intent
import decision.decision.mvi.Label
import decision.decision.mvi.State
import kotlinx.coroutines.flow.collectLatest

@Composable
fun Screen(
    component: Component,
) {
    val state by component.state.collectAsState()

    LaunchedEffect(Unit) {
        component.labels.collectLatest { label ->
            when (label) {
                Label.GoToSolutions -> component.onGoToSolutions()
                Label.Restart -> component.onRestart()
            }
        }
    }

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
            text = state.decision,
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