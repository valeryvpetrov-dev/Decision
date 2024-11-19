package dev.valeryvpetrov.decision.feature.hello_world.ui.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.valeryvpetrov.decision.feature.hello_world.presentation.component.HelloWorldComponent
import dev.valeryvpetrov.decision.feature.hello_world.presentation.component.HelloWorldComponentPreview
import dev.valeryvpetrov.decision.feature.hello_world.presentation.mvi.HelloWorldIntent
import dev.valeryvpetrov.decision.feature.hello_world.presentation.mvi.HelloWorldState
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun HelloWorldScreen(
    component: HelloWorldComponent,
    modifier: Modifier = Modifier,
) {
    val state: HelloWorldState by component.state.collectAsState()
    Column(
        modifier = modifier
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        TextField(
            value = state.nameTextField.value,
            onValueChange = {
                component.accept(HelloWorldIntent.ChangeName(it))
            },
            label = {
                Text(state.nameTextField.label)
            },
            placeholder = {
                Text(state.nameTextField.placeholder)
            },
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = {
                component.accept(HelloWorldIntent.Greeting)
            },
            enabled = state.greetingButtonState.isEnabled,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(state.greetingButtonState.text)
        }
        state.greeting?.let {
            Text(
                text = it,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}


@Preview
@Composable
fun HelloWorldScreenPreview() {
    HelloWorldScreen(
        component = HelloWorldComponentPreview()
    )
}