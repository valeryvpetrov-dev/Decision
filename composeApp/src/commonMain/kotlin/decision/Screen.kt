package decision

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import model.Solution
import mvi.decision.Intent
import mvi.decision.State
import mvi.decision.ViewModel

@Composable
fun Screen(
    decisionViewModel: ViewModel = viewModel { ViewModel() },
) {
    val state by decisionViewModel.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        when (state) {
            is State.NoSolutions -> NoSolutionsStateScreen(
                state = state as State.NoSolutions,
                onChangeProblemDescription = {
                    decisionViewModel.accept(Intent.ChangeProblemDescription(it))
                },
                onAddNewSolution = {
                    decisionViewModel.accept(Intent.AddNewSolution)
                },
                onClear = {
                    decisionViewModel.accept(Intent.Clear)
                }
            )

            is State.WithSolutions.NoDecision -> WithSolutionsNoDecisionStateScreen(
                state = state as State.WithSolutions,
                onChangeProblemDescription = {
                    decisionViewModel.accept(Intent.ChangeProblemDescription(it))
                },
                onAddNewSolution = {
                    decisionViewModel.accept(Intent.AddNewSolution)
                },
                onSelectSolution = { solution ->
                    decisionViewModel.accept(Intent.SelectSolution(solution))
                },
                onChangeSolutionDescription = { solution, description ->
                    decisionViewModel.accept(
                        Intent.ChangeSolutionDescription(solution, description)
                    )
                },
                onMakeDecision = {
                    decisionViewModel.accept(Intent.MakeDecision)
                },
                onClear = {
                    decisionViewModel.accept(Intent.Clear)
                }
            )

            is State.WithSolutions.WithDecision -> WithSolutionsWithDecisionStateScreen(
                state = state as State.WithSolutions.WithDecision,
                onChangeProblemDescription = {
                    decisionViewModel.accept(Intent.ChangeProblemDescription(it))
                },
                onAddNewSolution = {
                    decisionViewModel.accept(Intent.AddNewSolution)
                },
                onSelectSolution = { solution ->
                    decisionViewModel.accept(Intent.SelectSolution(solution))
                },
                onChangeSolutionDescription = { solution, description ->
                    decisionViewModel.accept(
                        Intent.ChangeSolutionDescription(solution, description)
                    )
                },
                onMakeDecision = {
                    decisionViewModel.accept(Intent.MakeDecision)
                },
                onClear = {
                    decisionViewModel.accept(Intent.Clear)
                }
            )
        }
    }
}

@Composable
private fun NoSolutionsStateScreen(
    state: State.NoSolutions,
    onChangeProblemDescription: (String) -> Unit,
    onAddNewSolution: () -> Unit,
    onClear: () -> Unit,
) {
    ProblemTextField(
        value = state.problem.description,
        onValueChange = onChangeProblemDescription
    )
    AddSolutionButton(
        onClick = onAddNewSolution
    )
    MakeDecisionButton(
        isEnabled = state.isMakeDecisionEnabled,
        onClick = {}
    )
    ClearButton(
        onClick = onClear,
    )
}

@Composable
private fun WithSolutionsNoDecisionStateScreen(
    state: State.WithSolutions,
    onChangeProblemDescription: (String) -> Unit,
    onAddNewSolution: () -> Unit,
    onSelectSolution: (Solution) -> Unit,
    onChangeSolutionDescription: (solution: Solution, description: String) -> Unit,
    onMakeDecision: () -> Unit,
    onClear: () -> Unit,
) {
    ProblemTextField(
        value = state.problem.description,
        onValueChange = onChangeProblemDescription
    )
    AddSolutionButton(
        onClick = onAddNewSolution
    )
    state.solutions.forEach { solution ->
        SolutionRow(
            text = solution.description,
            isSelected = solution.isSelected,
            onSelect = {
                onSelectSolution.invoke(solution)
            },
            onTextChange = { text ->
                onChangeSolutionDescription.invoke(solution, text)
            },
        )
    }
    MakeDecisionButton(
        isEnabled = state.isMakeDecisionEnabled,
        onClick = onMakeDecision
    )
    ClearButton(
        onClick = onClear,
    )
}

@Composable
private fun WithSolutionsWithDecisionStateScreen(
    state: State.WithSolutions.WithDecision,
    onChangeProblemDescription: (String) -> Unit,
    onAddNewSolution: () -> Unit,
    onSelectSolution: (Solution) -> Unit,
    onChangeSolutionDescription: (solution: Solution, description: String) -> Unit,
    onMakeDecision: () -> Unit,
    onClear: () -> Unit,
) {
    ProblemTextField(
        value = state.problem.description,
        onValueChange = onChangeProblemDescription
    )
    AddSolutionButton(
        onClick = onAddNewSolution
    )
    state.solutions.forEach { solution ->
        SolutionRow(
            text = solution.description,
            isSelected = solution.isSelected,
            onSelect = {
                onSelectSolution.invoke(solution)
            },
            onTextChange = { text ->
                onChangeSolutionDescription.invoke(solution, text)
            },
        )
    }
    MakeDecisionButton(
        isEnabled = state.isMakeDecisionEnabled,
        onClick = onMakeDecision
    )
    ClearButton(
        onClick = onClear,
    )
    Text("Decision Made: ${state.decisionMessage}")
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
            modifier = Modifier.fillMaxWidth(),
            value = text,
            onValueChange = onTextChange,
            label = { Text("Solution") }
        )
    }
}

@Composable
private fun MakeDecisionButton(
    isEnabled: Boolean,
    onClick: () -> Unit,
) {
    Button(
        modifier = Modifier.fillMaxWidth(),
        enabled = isEnabled,
        onClick = onClick
    ) {
        Text(text = "Make decision")
    }
}

@Composable
private fun ClearButton(
    onClick: () -> Unit,
) {
    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = onClick
    ) {
        Text(text = "Clear")
    }
}