package problem

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import decision.problem.component.Component
import decision.problem.mvi.Intent
import decision.problem.mvi.Label
import decision.problem.mvi.State
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
            }
        }
    }

    ScreenContent(
        state = state,
        onChangeProblemDescription = { description ->
            component.accept(Intent.ChangeProblemDescription(description))
        },
        onGoToSolutionsClick = {
            component.accept(Intent.GoToSolutions)
        }
    )
}

@Composable
private fun ScreenContent(
    state: State,
    onChangeProblemDescription: (description: String) -> Unit,
    onGoToSolutionsClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            ProblemTextField(
                value = state.description,
                onValueChange = onChangeProblemDescription
            )
        }
        Button(
            modifier = Modifier
                .fillMaxWidth(),
            onClick = onGoToSolutionsClick,
            enabled = state.isGoToSolutionsEnabled,
        ) {
            Text("To solutions")
        }
    }
}

@Composable
private fun ProblemTextField(value: String, onValueChange: (String) -> Unit) {
    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = value,
        onValueChange = onValueChange,
        label = { Text("Problem") }
    )
}