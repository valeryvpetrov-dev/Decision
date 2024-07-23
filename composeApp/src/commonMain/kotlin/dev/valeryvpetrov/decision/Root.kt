package dev.valeryvpetrov.decision

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.valeryvpetrov.decision.feature.make_decision.api.Component
import dev.valeryvpetrov.decision.feature.make_decision.ui.compose.Screen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun Root(
    component: Component,
    modifier: Modifier = Modifier,
) {
    MaterialTheme {
        Surface(
            modifier = modifier
                .fillMaxSize()
                .windowInsetsPadding(WindowInsets.systemBars)
        ) {
            Screen(component)
        }
    }
}
