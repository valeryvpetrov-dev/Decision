import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.extensions.compose.lifecycle.LifecycleController
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.arkivanov.mvikotlin.timetravel.server.TimeTravelServer
import decision.component.RealComponent
import javax.swing.SwingUtilities

fun main() {
    val lifecycle = LifecycleRegistry()
    val component = RealComponent(
        componentContext = DefaultComponentContext(lifecycle = lifecycle),
    )
    application {
        TimeTravelServer(runOnMainThread = { SwingUtilities.invokeLater(it) })
            .start()

        val windowState = rememberWindowState()
        LifecycleController(lifecycle, windowState)
        Window(
            onCloseRequest = ::exitApplication,
            state = windowState,
            title = "Decision",
        ) {
            App(
                component = component
            )
        }
    }
}