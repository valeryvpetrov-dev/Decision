package dev.valeryvpetrov.decision.feature.solution.ui.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.RadioButton
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dev.valeryvpetrov.decision.feature.solution.presentation.component.SolutionComponent
import dev.valeryvpetrov.decision.feature.solution.presentation.mvi.SolutionIntent
import dev.valeryvpetrov.decision.feature.solution.presentation.mvi.SolutionLabel
import dev.valeryvpetrov.decision.feature.solution.presentation.mvi.SolutionState
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun Screen(
    component: SolutionComponent,
) {
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    val state by component.state.collectAsState()
    LaunchedEffect(Unit) {
        component.labels.collectLatest { label ->
            when (label) {
                is SolutionLabel.OnAddNewSolutionFailure -> {
                    scope.launch {
                        snackbarHostState.showSnackbar(
                            message = label.message,
                            duration = SnackbarDuration.Short
                        )
                    }
                }
            }
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
    ) { contentPadding ->
        ScreenContent(
            modifier = Modifier.padding(contentPadding),
            state = state,
            onAddNewSolution = {
                component.accept(SolutionIntent.AddNewSolution)
            },
            onSuggestNewSolution = {
                component.accept(SolutionIntent.SuggestNewSolution)
            },
            onSelectSolution = { id ->
                component.accept(SolutionIntent.SelectSolution(id))
            },
            onDeleteSolution = { id ->
                component.accept(SolutionIntent.DeleteSolution(id))
            },
            onChangeSolutionDescription = { id, description ->
                component.accept(
                    SolutionIntent.ChangeSolutionDescription(
                        id = id,
                        description = description
                    )
                )
            },
            onGoToProblem = {
                component.accept(SolutionIntent.GoToProblem)
            },
            onGoToDecision = {
                component.accept(SolutionIntent.GoToDecision)
            }
        )
    }
}

@Composable
private fun ScreenContent(
    modifier: Modifier,
    state: SolutionState,
    onAddNewSolution: () -> Unit,
    onSuggestNewSolution: () -> Unit,
    onSelectSolution: (id: Int) -> Unit,
    onDeleteSolution: (id: Int) -> Unit,
    onChangeSolutionDescription: (id: Int, description: String) -> Unit,
    onGoToProblem: () -> Unit,
    onGoToDecision: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        AddSolutionButton(
            onClick = onAddNewSolution
        )
        SuggestSolutionButton(
            onClick = onSuggestNewSolution,
            isEnabled = state.isSuggestSolutionEnabled,
        )
        state.solutions.forEach { solution ->
            key(solution.id) {
                SolutionRow(
                    text = solution.description,
                    isSelected = solution.isSelected,
                    onSelect = {
                        onSelectSolution.invoke(solution.id)
                    },
                    onDelete = {
                        onDeleteSolution.invoke(solution.id)
                    },
                    onTextChange = { text ->
                        onChangeSolutionDescription.invoke(solution.id, text)
                    },
                )
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                modifier = Modifier
                    .weight(1f),
                onClick = onGoToProblem,
                enabled = state.isGoToProblemEnabled,
            ) {
                Text("To problem")
            }
            Button(
                modifier = Modifier
                    .weight(1f),
                onClick = onGoToDecision,
                enabled = state.isGoToDecisionEnabled,
            ) {
                Text("To decision")
            }
        }
    }
}

@Composable
private fun AddSolutionButton(onClick: () -> Unit) {
    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = onClick
    ) {
        Text(text = "Add solution")
    }
}

@Composable
private fun SuggestSolutionButton(
    onClick: () -> Unit,
    isEnabled: Boolean,
) {
    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = onClick,
        enabled = isEnabled,
    ) {
        Text(text = "Suggest solution")
    }
}

@Composable
private fun SolutionRow(
    text: String,
    isSelected: Boolean,
    onSelect: () -> Unit,
    onDelete: () -> Unit,
    onTextChange: (text: String) -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        RadioButton(
            selected = isSelected,
            onClick = onSelect
        )
        TextField(
            modifier = Modifier.weight(1f),
            value = text,
            onValueChange = onTextChange,
            label = { Text("Solution") }
        )
        IconButton(onClick = onDelete) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "Close Icon",
                tint = Color.Black,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}