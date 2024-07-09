package dev.valeryvpetrov.decision

import App
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.arkivanov.decompose.defaultComponentContext
import decision.component.RealComponent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val component = RealComponent(componentContext = defaultComponentContext())

        setContent {
            App(
                component = component
            )
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}