import androidx.compose.ui.window.ComposeUIViewController
import dev.valeryvpetrov.decision.feature.hello_world.presentation.component.HelloWorldComponent
import dev.valeryvpetrov.decision.feature.hello_world.sample.Root

fun MainViewController(component: HelloWorldComponent) = ComposeUIViewController {
    Root(component = component)
}