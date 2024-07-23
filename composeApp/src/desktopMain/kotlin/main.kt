import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.extensions.compose.lifecycle.LifecycleController
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.arkivanov.mvikotlin.timetravel.server.TimeTravelServer
import dev.valeryvpetrov.decision.Root
import dev.valeryvpetrov.decision.feature.make_decision.api.Component
import dev.valeryvpetrov.decision.umbrella.di.startKoin
import javax.swing.SwingUtilities
import org.koin.core.logger.Level

fun main() {
    val koin = startKoin {
        printLogger(Level.DEBUG)
    }
    // FIXME provide through DI
    val lifecycle = LifecycleRegistry()
    val factory = koin.koin.get<Component.Factory>()
    val component = factory.create(
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
            Root(
                component = component
            )
        }
    }
}