package dev.valeryvpetrov.decision.feature.history.ui.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.valeryvpetrov.decision.feature.history.api.model.History
import dev.valeryvpetrov.decision.feature.history.presentation.component.HistoryComponent
import dev.valeryvpetrov.decision.feature.history.presentation.mvi.HistoryState
import kotlinx.coroutines.flow.collectLatest

@Composable
fun HistoryScreen(component: HistoryComponent, modifier: Modifier = Modifier) {
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    val state by component.state.collectAsState()

    LaunchedEffect(Unit) {
        component.labels.collectLatest { label ->
            Unit
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        modifier = modifier,
    ) { contentPadding ->
        ScreenContent(
            modifier = Modifier.padding(contentPadding),
            state = state,
        )
    }
}

@Composable
private fun ScreenContent(modifier: Modifier = Modifier, state: HistoryState) {
    when (state) {
        is HistoryState.Content -> {
            LazyColumn(
                modifier = modifier,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                itemsIndexed(state.historyList) { i, item ->
                    HistoryItem(item)
                    if (i < state.historyList.lastIndex) {
                        Divider()
                    }
                }
            }
        }

        is HistoryState.NoContent -> {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "History of made decisions will be displayed here.",
                    style = MaterialTheme.typography.caption
                )
            }
        }

        HistoryState.Error -> Unit
        HistoryState.Loading -> Unit
    }
}

@Composable
fun HistoryItem(history: History) {
    Text("${history.id}: ${history.decisionMessage}")
}
