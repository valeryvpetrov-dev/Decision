import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.extensions.compose.lifecycle.LifecycleController
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.arkivanov.mvikotlin.core.utils.setMainThreadId
import com.arkivanov.mvikotlin.timetravel.server.TimeTravelServer
import dev.valeryvpetrov.decision.feature.hello_world.presentation.component.HelloWorldComponent
import dev.valeryvpetrov.decision.feature.hello_world.sample.Root
import dev.valeryvpetrov.decision.feature.hello_world.sample.startKoin
import javax.swing.SwingUtilities
import org.koin.core.logger.Level

fun main() {
    val koin = startKoin {
        printLogger(Level.DEBUG)
    }
    // FIXME provide through DI
    val lifecycle = LifecycleRegistry()
    val factory = koin.koin.get<HelloWorldComponent.Factory>()

    lateinit var component: HelloWorldComponent
    SwingUtilities.invokeAndWait {
        setMainThreadId(Thread.currentThread().id)
        component = factory.create(
            componentContext = DefaultComponentContext(lifecycle = lifecycle),
        )
    }
    application {
        TimeTravelServer(runOnMainThread = { SwingUtilities.invokeLater(it) })
            .start()
        val windowState = rememberWindowState()
        LifecycleController(lifecycle, windowState)
        Window(
            onCloseRequest = ::exitApplication,
            state = windowState,
            title = "Hello, World",
        ) {
            Root(
                component = component
            )
        }
    }
}