import androidx.compose.ui.window.ComposeUIViewController
import decision.component.Component

fun MainViewController(component: Component) = ComposeUIViewController {
    App(component = component)
}