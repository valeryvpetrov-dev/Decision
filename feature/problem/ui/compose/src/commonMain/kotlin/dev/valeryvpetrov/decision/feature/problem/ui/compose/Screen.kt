package dev.valeryvpetrov.decision.feature.problem.ui.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.valeryvpetrov.decision.feature.problem.presentation.component.ProblemComponent
import dev.valeryvpetrov.decision.feature.problem.presentation.mvi.ProblemIntent
import dev.valeryvpetrov.decision.feature.problem.presentation.mvi.ProblemState

@Composable
fun Screen(
    component: ProblemComponent,
) {
    val state by component.state.collectAsState()
    ScreenContent(
        state = state,
        onChangeProblemDescription = { description ->
            component.accept(ProblemIntent.ChangeProblemDescription(description))
        },
        onGoToSolutionsClick = {
            component.accept(ProblemIntent.GoToSolutions)
        }
    )
}

@Composable
private fun ScreenContent(
    state: ProblemState,
    onChangeProblemDescription: (description: String) -> Unit,
    onGoToSolutionsClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            ProblemTextField(
                value = state.problemTextFieldState.value,
                label = state.problemTextFieldState.label,
                placeholder = state.problemTextFieldState.placeholder,
                onValueChange = onChangeProblemDescription,
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
private fun ProblemTextField(
    value: String,
    label: String,
    placeholder: String,
    onValueChange: (String) -> Unit,
) {
    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(label)
        },
        placeholder = {
            Text(placeholder)
        }
    )
}
