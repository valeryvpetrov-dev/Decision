package dev.valeryvpetrov.decision

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.arkivanov.decompose.defaultComponentContext
import dev.valeryvpetrov.decision.feature.make_decision.presentation.component.MakeDecisionComponent
import org.koin.android.ext.android.getKoin

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = getKoin().get<MakeDecisionComponent.Factory>()
        val component = factory.create(
            componentContext = defaultComponentContext()
        )
        setContent {
            Root(
                component = component
            )
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    // FIXME
//    Root()
}