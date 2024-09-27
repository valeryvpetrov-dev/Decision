import androidx.compose.ui.window.ComposeUIViewController
import dev.valeryvpetrov.decision.Root
import dev.valeryvpetrov.decision.feature.make_decision.presentation.component.MakeDecisionComponent

fun MainViewController(component: MakeDecisionComponent) = ComposeUIViewController {
    Root(component = component)
}