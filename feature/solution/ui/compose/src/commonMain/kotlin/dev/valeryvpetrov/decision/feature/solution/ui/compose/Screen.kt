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
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dev.valeryvpetrov.decision.feature.solution.presentation.component.SolutionComponent
import dev.valeryvpetrov.decision.feature.solution.presentation.mvi.Intent
import dev.valeryvpetrov.decision.feature.solution.presentation.mvi.State

@Composable
fun Screen(
    component: SolutionComponent,
) {
    val state by component.state.collectAsState()
    ScreenContent(
        state = state,
        onAddNewSolution = {
            component.accept(Intent.AddNewSolution)
        },
        onSelectSolution = { index ->
            component.accept(Intent.SelectSolution(index))
        },
        onDeleteSolution = { index ->
            component.accept(Intent.DeleteSolution(index))
        },
        onChangeSolutionDescription = { index, description ->
            component.accept(
                Intent.ChangeSolutionDescription(
                    index = index,
                    description = description
                )
            )
        },
        onGoToProblem = {
            component.accept(Intent.GoToProblem)
        },
        onGoToDecision = {
            component.accept(Intent.GoToDecision)
        }
    )
}

@Composable
private fun ScreenContent(
    state: State,
    onAddNewSolution: () -> Unit,
    onSelectSolution: (index: Int) -> Unit,
    onDeleteSolution: (index: Int) -> Unit,
    onChangeSolutionDescription: (index: Int, description: String) -> Unit,
    onGoToProblem: () -> Unit,
    onGoToDecision: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        AddSolutionButton(
            onClick = onAddNewSolution
        )
        state.solutions.forEachIndexed { index, solution ->
            SolutionRow(
                text = solution.description,
                isSelected = solution.isSelected,
                onSelect = {
                    onSelectSolution.invoke(index)
                },
                onDelete = {
                    onDeleteSolution.invoke(index)
                },
                onTextChange = { text ->
                    onChangeSolutionDescription.invoke(index, text)
                },
            )
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