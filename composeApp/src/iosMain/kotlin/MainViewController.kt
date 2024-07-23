import androidx.compose.ui.window.ComposeUIViewController
import dev.valeryvpetrov.decision.Root
import dev.valeryvpetrov.decision.feature.make_decision.api.Component

fun MainViewController(component: Component) = ComposeUIViewController {
    Root(component = component)
}