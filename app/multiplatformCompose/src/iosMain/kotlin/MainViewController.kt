import androidx.compose.ui.window.ComposeUIViewController
import dev.valeryvpetrov.decision.Root
import dev.valeryvpetrov.decision.feature.tabs.presentation.component.TabsComponent

fun MainViewController(component: TabsComponent) = ComposeUIViewController {
    Root(component = component)
}