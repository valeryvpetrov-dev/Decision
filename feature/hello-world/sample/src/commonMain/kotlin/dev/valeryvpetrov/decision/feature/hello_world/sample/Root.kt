package dev.valeryvpetrov.decision.feature.hello_world.sample

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.valeryvpetrov.decision.feature.hello_world.presentation.component.HelloWorldComponent
import dev.valeryvpetrov.decision.feature.hello_world.ui.compose.HelloWorldScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun Root(
    component: HelloWorldComponent,
    modifier: Modifier = Modifier,
) {
    MaterialTheme {
        Surface(
            modifier = modifier
                .fillMaxSize()
                .windowInsetsPadding(WindowInsets.systemBars)
        ) {
            HelloWorldScreen(component)
        }
    }
}
