import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import decision.component.Component
import org.jetbrains.compose.ui.tooling.preview.Preview
import decision.Screen as DecisionScreen
import problem.Screen as ProblemScreen
import solutions.Screen as SolutionsScreen

@Composable
@Preview
fun App(
    component: Component,
    modifier: Modifier = Modifier,
) {
    MaterialTheme {
        Surface(
            modifier = modifier
                .fillMaxSize()
                .windowInsetsPadding(WindowInsets.systemBars)
        ) {
            Children(
                stack = component.childStack,
                modifier = Modifier.fillMaxSize(),
                animation = stackAnimation(slide())
            ) {
                when (val instance = it.instance) {
                    is Component.Child.Decision -> DecisionScreen(instance.component)
                    is Component.Child.Problem -> ProblemScreen(instance.component)
                    is Component.Child.Solutions -> SolutionsScreen(instance.component)
                }
            }
        }
    }
}
