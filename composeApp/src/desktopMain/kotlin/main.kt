import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.arkivanov.mvikotlin.timetravel.server.TimeTravelServer
import javax.swing.SwingUtilities

fun main() {
    application {
        TimeTravelServer(runOnMainThread = { SwingUtilities.invokeLater(it) })
            .start()
        Window(
            onCloseRequest = ::exitApplication,
            title = "Decision",
        ) {
            App()
        }
    }
}